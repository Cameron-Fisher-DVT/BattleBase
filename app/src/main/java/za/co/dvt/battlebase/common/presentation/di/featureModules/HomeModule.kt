package za.co.dvt.battlebase.common.presentation.di.featureModules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import za.co.dvt.battlebase.common.data.local.database.ApplicationDatabase
import za.co.dvt.battlebase.features.home.data.local.dataSource.PokemonLocalDataSource
import za.co.dvt.battlebase.features.home.data.local.dataSource.PokemonLocalDataSourceImpl
import za.co.dvt.battlebase.features.home.data.remote.adapter.PokeApiServiceAdapter
import za.co.dvt.battlebase.features.home.data.remote.dataSource.PokemonRemoteDataSource
import za.co.dvt.battlebase.features.home.data.remote.dataSource.PokemonRemoteDataSourceImpl
import za.co.dvt.battlebase.features.home.data.remote.implementation.PokeApiImpl
import za.co.dvt.battlebase.features.home.data.remote.infrastructure.PokeApiRetrofitClient
import za.co.dvt.battlebase.features.home.data.repository.PokemonRepositoryImpl
import za.co.dvt.battlebase.features.home.domain.repository.PokemonRepository
import za.co.dvt.battlebase.features.home.domain.usecase.FetchPokemonInformationUseCase
import za.co.dvt.battlebase.features.home.domain.usecase.FetchPokemonListUseCase
import za.co.dvt.battlebase.features.home.domain.usecase.SavePokemonListUseCase
import za.co.dvt.battlebase.features.home.domain.usecase.SavePokemonUseCase
import za.co.dvt.battlebase.features.home.presentation.HomeInformationScreenViewModel
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel

val homeModule = module {
    factory<PokemonLocalDataSource> {
        PokemonLocalDataSourceImpl(get<ApplicationDatabase>().pokemonDao(), get())
    }

    factory {
        PokeApiRetrofitClient()
    }

    factory { PokeApiImpl(get()) }

    factory { PokeApiServiceAdapter(get()) }

    factory<PokemonRemoteDataSource> {
        PokemonRemoteDataSourceImpl(get())
    }

    factory<PokemonRepository> {
        PokemonRepositoryImpl(get(), get())
    }

    factory {
        SavePokemonListUseCase(get())
    }

    factory {
        SavePokemonUseCase(get())
    }

    factory {
        FetchPokemonListUseCase(get())
    }

    factory {
        FetchPokemonInformationUseCase(get())
    }

    viewModel {
        HomeScreenViewModel(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        HomeInformationScreenViewModel(get(), get())
    }
}