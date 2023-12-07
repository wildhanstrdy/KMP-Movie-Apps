package ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint

abstract class KtorHttpClient {
    abstract fun getHttpClient(): HttpClient
}

class CIOHttpClient : KtorHttpClient() {
    override fun getHttpClient(): HttpClient {
        return HttpClient(CIO) {
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

    companion object{
        const val TAG = "cioHttpClient"
    }
}
