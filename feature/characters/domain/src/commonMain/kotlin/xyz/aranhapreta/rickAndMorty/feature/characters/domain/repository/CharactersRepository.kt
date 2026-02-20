package xyz.aranhapreta.rickAndMorty.feature.characters.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Character

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}
