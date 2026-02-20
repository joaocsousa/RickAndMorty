package xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models

import androidx.compose.runtime.Stable
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Status

@Stable
internal sealed interface CharacterStatus {
    data object Alive : CharacterStatus
    data object Dead : CharacterStatus
    data object Unknown : CharacterStatus
}

internal fun Status.toState() = when (this) {
    Status.Alive -> CharacterStatus.Alive
    Status.Dead -> CharacterStatus.Dead
    Status.Unknown -> CharacterStatus.Unknown
}