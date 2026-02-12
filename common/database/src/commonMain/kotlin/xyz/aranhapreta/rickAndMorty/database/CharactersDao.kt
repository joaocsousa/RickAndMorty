package xyz.aranhapreta.rickAndMorty.database

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.database.models.CharacterDbModel
import xyz.aranhapreta.rickAndMorty.database.models.CharacterPaginationDbModel

interface CharactersDao {
    fun observeAllCharacters(): Flow<List<CharacterDbModel>>
    suspend fun countAll(): Int
    suspend fun getPaginationInfo(): CharacterPaginationDbModel?
    suspend fun insertOrUpdateCharacters(characters: List<CharacterDbModel>)
    suspend fun insertOrUpdatePaginationInfo(pagination: CharacterPaginationDbModel)
}