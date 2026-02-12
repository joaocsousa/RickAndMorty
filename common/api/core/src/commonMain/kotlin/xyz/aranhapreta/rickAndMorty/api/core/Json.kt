package xyz.aranhapreta.rickAndMorty.api.core

import kotlinx.serialization.json.Json

internal fun json() = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
    explicitNulls = false
}