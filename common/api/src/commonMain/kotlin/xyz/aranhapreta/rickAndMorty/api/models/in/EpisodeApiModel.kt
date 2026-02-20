package xyz.aranhapreta.rickAndMorty.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeApiModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
