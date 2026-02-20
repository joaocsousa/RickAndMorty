package xyz.aranhapreta.rickAndMorty.feature.characters.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import co.touchlab.kermit.Logger
import xyz.aranhapreta.rickAndMorty.api.CharactersApi
import xyz.aranhapreta.rickAndMorty.api.models.`in`.CharacterApiModel
import xyz.aranhapreta.rickAndMorty.database.Character
import xyz.aranhapreta.rickAndMorty.database.CharactersDao
import xyz.aranhapreta.rickAndMorty.database.RemoteKeysDao
import xyz.aranhapreta.rickAndMorty.database.RemoteKeysDao.QueryType.Characters
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalPagingApi::class)
internal class CharactersRemoteMediator(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
    private val remoteKeysDao: RemoteKeysDao,
) : RemoteMediator<Int, Character>() {

    override suspend fun initialize(): InitializeAction {
        val characterCount = charactersDao.countAll()
        Logger.i { "RemoteMediator initialize(). Character count in DB: $characterCount" }
        return if (characterCount == 0) {
            Logger.i { "Database is empty. LAUNCH_INITIAL_REFRESH" }
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            Logger.i { "Database has data. SKIP_INITIAL_REFRESH" }
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult = try {
        Logger.d { "RemoteMediator load() called with loadType: $loadType" }

        val loadKey: Long? = when (loadType) {
            LoadType.REFRESH -> {
                Logger.d { "LoadType is REFRESH. Setting loadKey to 1." }
                1L
            }
            LoadType.PREPEND -> {
                Logger.d { "LoadType is PREPEND. End of pagination reached." }
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val remoteKey = remoteKeysDao.getRemoteKeyByType(Characters)
                if (remoteKey == null) {
                    Logger.d { "LoadType is APPEND, but no remote key found. Waiting for REFRESH to complete." }
                    return MediatorResult.Success(endOfPaginationReached = false)
                }

                if (remoteKey.nextKey == null) {
                    Logger.d { "LoadType is APPEND, but nextKey is null. End of pagination reached." }
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                Logger.d { "LoadType is APPEND. Loading next page with key: ${remoteKey.nextKey}" }
                remoteKey.nextKey
            }
        }

        Logger.i { "Fetching characters from API with page: $loadKey" }
        val response = charactersApi.getCharacters(page = loadKey ?: 1L).getOrThrow()
        val endOfPaginationReached = response.pagination.next == null
        Logger.i { "API response received. Fetched ${response.results.size} characters. End of pagination: $endOfPaginationReached" }


        if (loadType == LoadType.REFRESH) {
            Logger.i { "Clearing all characters and remote keys from the database." }
            remoteKeysDao.clearAllRemoteKeys()
            charactersDao.clearAllCharacters()
        }

        val nextKey = if (endOfPaginationReached) null else (loadKey ?: 1L) + 1
        Logger.d { "Inserting new remote key. Type: ${Characters}, nextKey: $nextKey" }
        remoteKeysDao.insertKey(type = Characters, nextKey = nextKey)

        Logger.d { "Inserting ${response.results.size} characters into the database." }
        charactersDao.insertOrUpdateCharacters(
            characters = response.results.toDbModel()
        )
        Logger.i { "Load successful. EndOfPaginationReached: $endOfPaginationReached" }
        MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

    } catch (e: CancellationException) {
        Logger.w(e) { "Coroutine Cancellation in RemoteMediator" }
        throw e
    } catch (e: Exception) {
        Logger.e(e) { "Error in CharacterRemoteMediator load(): ${e.message}" }
        MediatorResult.Error(e)
    }
}

private fun List<CharacterApiModel>.toDbModel() = map { apiModel ->
    Character(
        name = apiModel.name,
        image = apiModel.image,
        status = apiModel.status,
        species = apiModel.species,
        type = apiModel.type,
        gender = apiModel.gender,
        id = apiModel.id.toLong(),
    )
}
