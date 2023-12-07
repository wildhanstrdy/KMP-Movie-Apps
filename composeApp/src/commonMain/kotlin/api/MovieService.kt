package api

import io.ktor.client.HttpClient

interface MovieService {
    suspend fun getMovieList()
}

internal class MovieServiceImpl constructor(
    private val httpClient: HttpClient
) : MovieService {
    override suspend fun getMovieList() {
        TODO("Not yet implemented")
    }

}
