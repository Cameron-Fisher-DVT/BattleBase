package za.co.dvt.battlebase.features.home.data.remote.dataSource

import za.co.dvt.battlebase.common.data.remote.common.ApiResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse

interface PokemonRemoteDataSource {
    suspend fun fetchPokemonList(offset: Int, limit: Int): ApiResponse<PokemonResponse>
    suspend fun fetchPokemonInformationResponse(pokemonId: String): ApiResponse<PokemonInformationResponse>
}