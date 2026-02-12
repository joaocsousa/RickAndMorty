package xyz.aranhapreta.rickAndMorty.api.core

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val BASE_URL = "https://rickandmortyapi.com/api/"

val apiCoreModule = module {
    single<Json> {
        json()
    }
    single<HttpClient> {
        httpClient(baseUrl = BASE_URL, json = get())
    }
}
