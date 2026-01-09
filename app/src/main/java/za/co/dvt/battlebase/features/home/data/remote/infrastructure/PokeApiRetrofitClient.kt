package za.co.dvt.battlebase.features.home.data.remote.infrastructure

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import za.co.dvt.battlebase.common.data.remote.config.ApiConstants
import za.co.dvt.battlebase.features.home.data.remote.api.PokeApiService
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse

class PokeApiRetrofitClient {
    val pokeApiServiceRetrofitClient: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.POKE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    suspend fun fetchPokemonList(offset: Int, limit: Int): Response<PokemonResponse> {
        return pokeApiServiceRetrofitClient.fetchPokemonList(offset, limit)
    }

    suspend fun fetchPokemonInformation(pokemonId: String): Response<PokemonInformationResponse> {
        return pokeApiServiceRetrofitClient.fetchPokemonInformation(pokemonId)
    }
}