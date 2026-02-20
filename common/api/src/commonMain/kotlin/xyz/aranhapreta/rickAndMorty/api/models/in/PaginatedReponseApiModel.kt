package xyz.aranhapreta.rickAndMorty.api.models.`in`

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedReponseApiModel<T>(
    @SerialName("info")
    val pagination: PaginationApiModel,
    val results: List<T>
)