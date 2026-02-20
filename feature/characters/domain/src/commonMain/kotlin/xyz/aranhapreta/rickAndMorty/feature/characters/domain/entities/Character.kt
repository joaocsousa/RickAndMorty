package xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities

data class Character(
    val id: String,
    val name: String,
    val image: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
)