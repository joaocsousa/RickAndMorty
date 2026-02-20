package xyz.aranhapreta.rickAndMorty.api.core

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.Logger as KtorLogger

internal fun httpClient(baseUrl: String, json: Json) =
    HttpClient(httpEngineFactory) {
        install(Logging) {
            level = LogLevel.ALL
            val kermit = Logger.withTag("ktor")
            logger = object : KtorLogger {
                override fun log(message: String) {
                    kermit.i(message)
                }
            }
        }
        defaultRequest {
            header("Content-Type", "application/json")
            url(baseUrl)
        }
        install(ContentNegotiation) {
            json(json)
        }
    }

expect val httpEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>