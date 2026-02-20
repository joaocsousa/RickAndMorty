package xyz.aranhapreta.rickAndMorty.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class PaginationApiModel(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)