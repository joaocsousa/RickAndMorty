package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Character
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.usecases.ObserveCharacters
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterScreenEvent
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterScreenEvent.CharacterClicked
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterState
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.toState

internal class CharactersViewModel(
    observeCharacters: ObserveCharacters,
) : ViewModel() {

    init {
        Logger.i { "CharactersViewModel initialized" }
    }

    val characters: Flow<PagingData<CharacterState>> = observeCharacters()
        .map { pagingData ->
            Logger.d { "ViewModel received new PagingData from the use case." }
            pagingData.map { character ->
                character.toState()
            }
        }
        .cachedIn(viewModelScope)

    fun onEvent(event: CharacterScreenEvent) {
        Logger.d { "ViewModel onEvent received: $event" }
        when (event) {
            is CharacterClicked -> {
                // Handle character click
            }
        }
    }

    private fun Character.toState() = CharacterState(
        id = this.id,
        name = this.name,
        imageUrl = this.image,
        status = this.status.toState(),
        species = this.species,
        type = this.type,
        gender = this.gender.toState(),
    )
}
