package di

import org.kodein.di.DI

object Injector {
    val kodeinContainer = DI.lazy {
        importAll(dataSourceModule, repositoryModule, coreModule)
    }


}
