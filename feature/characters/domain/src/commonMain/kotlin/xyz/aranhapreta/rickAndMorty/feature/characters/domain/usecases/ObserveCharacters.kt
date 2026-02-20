package xyz.aranhapreta.rickAndMorty.feature.characters.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Character
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.repository.CharactersRepository

interface ObserveCharacters {
    operator fun invoke(): Flow<PagingData<Character>>
}

internal class ObserveCharactersImpl(
    private val charactersRepository: CharactersRepository
) : ObserveCharacters {
    override fun invoke(): Flow<PagingData<Character>> {
        return charactersRepository.getCharacters()
    }
}
