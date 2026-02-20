package xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities

sealed interface Status {
    data object Alive : Status
    data object Dead : Status
    data object Unknown : Status
}