package za.co.dvt.battlebase.common.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import za.co.dvt.battlebase.common.data.local.model.AbilityEntity
import za.co.dvt.battlebase.common.data.local.model.PokemonEntity
import za.co.dvt.battlebase.common.data.local.model.PokemonWithAbilitiesAndStats
import za.co.dvt.battlebase.common.data.local.model.StatEntity

@Dao
interface PokemonDao : BaseDao<PokemonEntity> {
    @Transaction
    @Query("SELECT * FROM pokemon WHERE id BETWEEN :offset AND :limit ORDER BY id ASC")
    suspend fun fetchAllPokemonWithAbilitiesAndStats(offset: Int, limit: Int): List<PokemonWithAbilitiesAndStats>

    @Insert(onConflict = REPLACE)
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertAbilityEntityList(abilityEntityList: List<AbilityEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertStatEntityList(statEntityList: List<StatEntity>)

    @Transaction
    suspend fun insertPokemonEntityWithAbilitiesAndStats(
        pokemonWithAbilitiesAndStats: PokemonWithAbilitiesAndStats
    ) {
        insertPokemon(pokemonWithAbilitiesAndStats.pokemon)

        val abilityEntityListWithId = pokemonWithAbilitiesAndStats.abilityList.map { it.copy(pokemonId = pokemonWithAbilitiesAndStats.pokemon.id) }
        insertAbilityEntityList(abilityEntityListWithId)

        val statEntityListWithId = pokemonWithAbilitiesAndStats.statList.map { it.copy(pokemonId = pokemonWithAbilitiesAndStats.pokemon.id) }
        insertStatEntityList(statEntityListWithId)
    }

    @Transaction
    suspend fun insertPokemonEntityListWithAbilitiesAndStats(
        pokemonWithAbilitiesAndStatsList: List<PokemonWithAbilitiesAndStats>
    ) {
        pokemonWithAbilitiesAndStatsList.forEach { pokemonWithAbilitiesAndStats ->
            insertPokemon(pokemonWithAbilitiesAndStats.pokemon)

            val abilityEntityListWithId = pokemonWithAbilitiesAndStats.abilityList.map { it.copy(pokemonId = pokemonWithAbilitiesAndStats.pokemon.id) }
            insertAbilityEntityList(abilityEntityListWithId)

            val statEntityListWithId = pokemonWithAbilitiesAndStats.statList.map { it.copy(pokemonId = pokemonWithAbilitiesAndStats.pokemon.id) }
            insertStatEntityList(statEntityListWithId)
        }
    }
}