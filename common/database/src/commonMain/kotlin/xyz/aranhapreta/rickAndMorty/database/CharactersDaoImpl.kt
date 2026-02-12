package xyz.aranhapreta.rickAndMorty.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import xyz.aranhapreta.rickAndMorty.database.models.CharacterDbModel
import xyz.aranhapreta.rickAndMorty.database.models.CharacterPaginationDbModel

internal class CharactersDaoImpl(
    database: Database,
    private val ioDispatcher: CoroutineDispatcher,
) : CharactersDao {

    private val characterQueries = database.charactersQueries
    private val paginationQueries = database.characters_paginationQueries

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeAllCharacters(): Flow<List<CharacterDbModel>> {
        return characterQueries
            .selectAllCharacters()
            .asFlow()
            .mapToList(ioDispatcher)
            .mapLatest { it.toDbModel() }
    }

    override suspend fun countAll(): Int {
        return withContext(ioDispatcher) {
            characterQueries.countAll().executeAsOne().toInt()
        }
    }

    override suspend fun getPaginationInfo(): CharacterPaginationDbModel? {
        return withContext(ioDispatcher) {
            paginationQueries.selectInfo().executeAsOneOrNull()
                ?.let {
                    CharacterPaginationDbModel(
                        currentPage = it.current_page.toInt(),
                        totalPages = it.total_pages.toInt()
                    )
                }
        }
    }

    override suspend fun insertOrUpdateCharacters(characters: List<CharacterDbModel>) {
        withContext(ioDispatcher) {
            characterQueries.transaction {
                characters.forEach { character ->
                    characterQueries.insertCharacter(
                        id = character.id.toLong(),
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

    override suspend fun insertOrUpdatePaginationInfo(pagination: CharacterPaginationDbModel) {
        withContext(ioDispatcher) {
            paginationQueries.upsertInfo(
                current_page = pagination.currentPage.toLong(),
                total_pages = pagination.totalPages.toLong()
            )
        }
    }

    private fun List<Character>.toDbModel() = map {
        CharacterDbModel(
            id = it.id.toInt(),
            name = it.name,
            status = it.status,
            species = it.species,
            type = it.type,
            gender = it.gender,
            image = it.image
        )
    }
}
