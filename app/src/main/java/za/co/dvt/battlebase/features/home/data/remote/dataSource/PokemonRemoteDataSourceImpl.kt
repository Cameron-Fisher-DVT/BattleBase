package za.co.dvt.battlebase.features.home.data.remote.dataSource

import za.co.dvt.battlebase.common.data.remote.common.ApiResponse
import za.co.dvt.battlebase.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.battlebase.features.home.data.remote.adapter.PokeApiServiceAdapter
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse

class PokemonRemoteDataSourceImpl(
    private val pokeApiServiceAdapter: PokeApiServiceAdapter
) : PokemonRemoteDataSource {
    override suspend fun fetchPokemonList(
        offset: Int,
        limit: Int
    ): ApiResponse<PokemonResponse> {
        return when(val networkResponse = pokeApiServiceAdapter.fetchPokemonList(offset, limit)) {
            is NetworkResponse.HttpError -> {
                ApiResponse.Error(networkResponse.message)
            }
            is NetworkResponse.NetworkError -> {
                ApiResponse.Error(networkResponse.exception.message ?: "")
            }
            is NetworkResponse.Success<PokemonResponse> -> {
                ApiResponse.Success(networkResponse.data)
            }
        }
    }

    override suspend fun fetchPokemonInformationResponse(pokemonId: String): ApiResponse<PokemonInformationResponse> {
        return when(val networkResponse = pokeApiServiceAdapter.fetchPokemonInformation(pokemonId)) {
            is NetworkResponse.HttpError -> {
                ApiResponse.Error(networkResponse.message)
            }
            is NetworkResponse.NetworkError -> {
                ApiResponse.Error(networkResponse.exception.message ?: "")
            }
            is NetworkResponse.Success<PokemonInformationResponse> -> {
                ApiResponse.Success(networkResponse.data)
            }
        }
    }
}