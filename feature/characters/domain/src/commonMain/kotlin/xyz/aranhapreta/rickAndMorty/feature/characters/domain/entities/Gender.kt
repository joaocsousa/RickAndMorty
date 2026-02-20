package xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities

sealed interface Gender {
    data object Female : Gender
    data object Male : Gender
    data object Genderless : Gender
    data object Unknown : Gender
}