package xyz.aranhapreta.rickAndMorty.database

import androidx.paging.PagingSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.paging3.QueryPagingSource
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface CharactersDao : DatabaseItemFetcher<Character> {
    fun observeAllCharacters(): Flow<List<Character>>
    suspend fun countAll(): Int
    suspend fun insertOrUpdateCharacters(characters: List<Character>)
    suspend fun clearAllCharacters()
    fun pagingSource(): PagingSource<Int, Character>
}

internal class CharactersDaoImpl(
    private val database: Database,
    private val ioDispatcher: CoroutineDispatcher,
) : CharactersDao {

    private val characterQueries = database.charactersQueries

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeAllCharacters(): Flow<List<Character>> {
        Logger.d { "Observing all characters" }
        return characterQueries
            .getAllCharacters()
            .asFlow()
            .mapToList(ioDispatcher)
    }

    override suspend fun countAll(): Int {
        return withContext(ioDispatcher) {
            val count = characterQueries.countAll().executeAsOne().toInt()
            Logger.d { "Counting all characters: $count" }
            count
        }
    }

    override suspend fun insertOrUpdateCharacters(characters: List<Character>) {
        withContext(ioDispatcher) {
            Logger.d { "Inserting ${characters.size} characters" }
            characterQueries.transaction {
                characters.forEach { character ->
                    characterQueries.insertCharacter(
                        id = character.id,
                        name = character.name,
                        image = character.image,
                        status = character.status,
                        species = character.species,
                        type = character.type,
                        gender = character.gender,
                    )
                }
            }
        }
    }

    override suspend fun clearAllCharacters() {
        return withContext(ioDispatcher) {
            val count = characterQueries.clearAllCharacters().value
            Logger.i { "Cleared $count characters" }
        }
    }

    override suspend fun getItems(
        limit: Int,
        offset: Int
    ): List<Character> {
        return withContext(ioDispatcher) {
            Logger.d { "Fetching characters with limit: $limit, offset: $offset" }
            val items = characterQueries.getCharactersWithLimitAndOffset(
                limit = limit.toLong(),
                offset = offset.toLong()
            ).executeAsList()
            Logger.d { "Fetched ${items.size} characters from DB" }
            items
        }
    }

    override fun pagingSource(): PagingSource<Int, Character> {
        return QueryPagingSource(
            countQuery = characterQueries.countAll(),
            transacter = characterQueries,
            context = ioDispatcher,
            queryProvider = { limit: Long, offset: Long ->
                characterQueries.getCharactersWithLimitAndOffset(
                    limit = limit,
                    offset = offset
                )
            },
        )
    }
}
