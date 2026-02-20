package xyz.aranhapreta.rickAndMorty.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class LocationRefApiModel(
    val name: String,
    val url: String
)