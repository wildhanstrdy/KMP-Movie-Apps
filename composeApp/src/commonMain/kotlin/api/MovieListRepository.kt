package api

import io.ktor.client.HttpClient

interface MovieListRepository {

}

internal class MovieListRepositoryImpl constructor(
    val httpClient: HttpClient
) : MovieListRepository {

}
