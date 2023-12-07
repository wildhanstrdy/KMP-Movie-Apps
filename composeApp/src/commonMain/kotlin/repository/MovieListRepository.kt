package repository

import api.MovieService
import io.ktor.client.HttpClient

interface MovieListRepository {

}

internal class MovieListRepositoryImpl constructor(
    val movieService: MovieService
) : MovieListRepository {

}
