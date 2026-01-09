package za.co.dvt.battlebase.features.home.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import za.co.dvt.battlebase.common.data.remote.config.ApiConstants
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse

interface PokeApiService {
    @GET(ApiConstants.POKE_API_POKEMON_ENDPOINT)
    suspend fun fetchPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonResponse>

    @GET("${ApiConstants.POKEMON_INFORMATION_ENDPOINT}{id}")
    suspend fun fetchPokemonInformation(
        @Path("id") pokemonId: String
    ): Response<PokemonInformationResponse>
}