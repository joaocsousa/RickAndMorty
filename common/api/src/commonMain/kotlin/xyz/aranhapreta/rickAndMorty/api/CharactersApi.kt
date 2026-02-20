package xyz.aranhapreta.rickAndMorty.api

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.aranhapreta.rickAndMorty.api.models.`in`.CharacterApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginatedReponseApiModel

interface CharactersApi {
    suspend fun getCharacters(page: Long?): Result<PaginatedReponseApiModel<CharacterApiModel>>
    suspend fun getCharacter(id: Int): Result<CharacterApiModel>
}

internal class CharactersApiImpl(
    private val client: HttpClient
) : CharactersApi {

    override suspend fun getCharacters(page: Long?): Result<PaginatedReponseApiModel<CharacterApiModel>> {
        return runCatching<CharactersApiImpl, PaginatedReponseApiModel<CharacterApiModel>> {
            client.get("character") {
                page?.let { parameter("page", it) }
            }.body()
        }.onSuccess {
            Logger.i { "Received ${it.results.size} characters" }
        }.onFailure {
            Logger.w(throwable = it) { "error getting characters for page $page" }
        }
    }

    override suspend fun getCharacter(id: Int): Result<CharacterApiModel> {
        return runCatching {
            client.get("character/$id").body<CharacterApiModel>()
        }.onSuccess {
            Logger.i { "Received character $it" }
        }.onFailure {
            Logger.w(throwable = it) { "error getting character $id" }
        }
    }
}