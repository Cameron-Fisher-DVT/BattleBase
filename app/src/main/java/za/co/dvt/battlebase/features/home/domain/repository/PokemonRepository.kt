package za.co.dvt.battlebase.features.home.domain.repository

import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.PokemonInformation

interface PokemonRepository {
    suspend fun savePokemonList(pokemonList: List<Pokemon>): Response<String>
    suspend fun savePokemon(pokemon: Pokemon): Response<String>
    suspend fun fetchPokemonList(offset: Int, limit: Int): Response<List<Pokemon>>
    suspend fun fetchPokemonInformation(pokemonId: String): Response<PokemonInformation>
}