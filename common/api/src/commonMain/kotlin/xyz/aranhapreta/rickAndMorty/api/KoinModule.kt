package xyz.aranhapreta.rickAndMorty.api

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import xyz.aranhapreta.rickAndMorty.api.core.httpClient
import xyz.aranhapreta.rickAndMorty.api.core.json

private const val BASE_URL = "https://rickandmortyapi.com/api/"

val apiCoreModule = module {
    single<Json> {
        json()
    }
    single<HttpClient> {
        httpClient(
            baseUrl = BASE_URL,
            json = get()
        )
    }
    factory<CharactersApi> {
        CharactersApiImpl(client = get())
    }
}
