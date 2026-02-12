package xyz.aranhapreta.rickAndMorty.api.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual val httpEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>
    get() = OkHttp