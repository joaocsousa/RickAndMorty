package xyz.aranhapreta.rickAndMorty.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class LocationApiModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)