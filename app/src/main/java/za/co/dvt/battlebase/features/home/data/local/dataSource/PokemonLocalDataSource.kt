package za.co.dvt.battlebase.features.home.data.local.dataSource

import za.co.dvt.battlebase.common.data.local.common.DatabaseResponse
import za.co.dvt.battlebase.common.data.local.model.PokemonWithAbilitiesAndStats

interface PokemonLocalDataSource {
    suspend fun savePokemonList(pokemonWithAbilitiesAndStatsList: List<PokemonWithAbilitiesAndStats>): DatabaseResponse<String>
    suspend fun savePokemon(pokemonWithAbilitiesAndStats: PokemonWithAbilitiesAndStats): DatabaseResponse<String>
}