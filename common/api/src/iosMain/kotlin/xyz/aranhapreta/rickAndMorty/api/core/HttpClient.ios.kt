package xyz.aranhapreta.rickAndMorty.api.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual val httpEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>
    get() = Darwin