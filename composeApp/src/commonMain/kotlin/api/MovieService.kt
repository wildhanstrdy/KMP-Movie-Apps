package api

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dto.MovieListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface MovieService {
    @NativeCoroutines
    suspend fun getPopularMovies(page: Int): MovieListResponse
}

private const val TMDB_BASE_URL = "https://api.themoviedb.org/3"

internal class MovieServiceImpl(
    private val httpClient: HttpClient
) : MovieService {

    override suspend fun getPopularMovies(page: Int): MovieListResponse {
        return httpClient.get("$TMDB_BASE_URL/movie/popular").body()
    }

}
