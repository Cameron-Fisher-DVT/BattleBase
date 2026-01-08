package za.co.dvt.battlebase.features.home.data.local.dataSource

import za.co.dvt.battlebase.R
import za.co.dvt.battlebase.common.data.local.common.DatabaseResponse
import za.co.dvt.battlebase.common.data.local.database.dao.PokemonDao
import za.co.dvt.battlebase.common.data.local.model.PokemonWithAbilitiesAndStats
import za.co.dvt.battlebase.common.presentation.manager.resourceManager.ResourceManager

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val resourceManager: ResourceManager
): PokemonLocalDataSource {
    override suspend fun savePokemonList(pokemonWithAbilitiesAndStatsList: List<PokemonWithAbilitiesAndStats>): DatabaseResponse<String> {
        pokemonDao.insertPokemonEntityListWithAbilitiesAndStats(pokemonWithAbilitiesAndStatsList)
        return DatabaseResponse.Success(resourceManager.getString(R.string.battle_base_successfully_saved))
    }

    override suspend fun savePokemon(pokemonWithAbilitiesAndStats: PokemonWithAbilitiesAndStats): DatabaseResponse<String> {
        pokemonDao.insertPokemonEntityWithAbilitiesAndStats(pokemonWithAbilitiesAndStats)
        return DatabaseResponse.Success(resourceManager.getString(R.string.battle_base_successfully_saved))
    }
}