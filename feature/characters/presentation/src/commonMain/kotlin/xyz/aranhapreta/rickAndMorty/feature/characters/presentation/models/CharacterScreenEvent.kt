package xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models

internal sealed interface CharacterScreenEvent {
    data class CharacterClicked(val id: String) : CharacterScreenEvent
}