package za.co.dvt.battlebase.features.home.data.remote.adapter

import za.co.dvt.battlebase.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.battlebase.common.data.remote.infrastructure.retrofit.RetrofitClientHelper
import za.co.dvt.battlebase.features.home.data.remote.api.PokeApiClient
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse
import za.co.dvt.battlebase.features.home.data.remote.implementation.PokeApiImpl

class PokeApiServiceAdapter(
    private val pokeApiImpl: PokeApiImpl
) : PokeApiClient {
    override suspend fun fetchPokemonList(
        offset: Int,
        limit: Int
    ): NetworkResponse<PokemonResponse> {
        return RetrofitClientHelper.serviceCall {
            pokeApiImpl.fetchPokemonList(offset, limit)
        }
    }

    override suspend fun fetchPokemonInformation(pokemonId: String): NetworkResponse<PokemonInformationResponse> {
        return RetrofitClientHelper.serviceCall {
            pokeApiImpl.fetchPokemonInformation(pokemonId)
        }
    }
}