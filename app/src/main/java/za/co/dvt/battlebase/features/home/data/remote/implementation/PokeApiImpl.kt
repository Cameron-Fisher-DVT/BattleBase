package za.co.dvt.battlebase.features.home.data.remote.implementation

import retrofit2.Response
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse
import za.co.dvt.battlebase.features.home.data.remote.infrastructure.PokeApiRetrofitClient

class PokeApiImpl(
    private val pokeApiRetrofitClient: PokeApiRetrofitClient
) {
    suspend fun fetchPokemonList(offset: Int, limit: Int): Response<PokemonResponse> {
        return pokeApiRetrofitClient.fetchPokemonList(offset, limit)
    }

    suspend fun fetchPokemonInformation(pokemonId: String): Response<PokemonInformationResponse> {
        return pokeApiRetrofitClient.fetchPokemonInformation(pokemonId)
    }
}