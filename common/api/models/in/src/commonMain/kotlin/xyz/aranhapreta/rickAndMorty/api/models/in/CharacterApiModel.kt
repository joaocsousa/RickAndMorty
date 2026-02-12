package xyz.aranhapreta.rickAndMorty.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class CharacterApiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationRefApiModel,
    val location: LocationRefApiModel,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
