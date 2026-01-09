package za.co.dvt.battlebase.features.home.data.remote.api

import za.co.dvt.battlebase.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse

interface PokeApiClient {
    suspend fun fetchPokemonList(
        offset: Int,
        limit: Int
    ): NetworkResponse<PokemonResponse>

    suspend fun fetchPokemonInformation(
        pokemonId: String
    ): NetworkResponse<PokemonInformationResponse>
}