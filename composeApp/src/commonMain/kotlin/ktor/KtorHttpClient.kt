package ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import keys.API_KEYS
import kotlinx.serialization.json.Json

abstract class KtorHttpClient {
    abstract fun getHttpClient(): HttpClient
}

class CIOHttpClient : KtorHttpClient() {
    override fun getHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(API_KEYS.TMDB_KEY, API_KEYS.TMDB_KEY)
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            engine {
                endpoint {
                    maxConnectionsCount = 1000
                    endpoint {
                        maxConnectionsPerRoute = 100
                        pipelineMaxSize = 20
                        keepAliveTime = 5000
                        connectTimeout = 5000
                        connectAttempts = 5
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "cioHttpClient"
    }
}
