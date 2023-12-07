package di

import api.MovieService
import api.MovieServiceImpl
import repository.MovieListRepository
import repository.MovieListRepositoryImpl
import io.ktor.client.HttpClient
import ktor.CIOHttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val coreModule = DI.Module("core") {
    bind<HttpClient>(tag = CIOHttpClient.TAG) with singleton {
        CIOHttpClient().getHttpClient()
    }
}

val dataSourceModule = DI.Module("dataSource") {
    bind<MovieService>() with singleton {
        MovieServiceImpl(instance())
    }
}

val repositoryModule = DI.Module("repository") {
    bind<MovieListRepository>() with provider {
        MovieListRepositoryImpl(instance(tag = CIOHttpClient.TAG))
    }
}
