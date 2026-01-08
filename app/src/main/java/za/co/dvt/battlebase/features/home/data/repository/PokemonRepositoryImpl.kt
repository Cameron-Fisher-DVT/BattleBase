package za.co.dvt.battlebase.features.home.data.repository

import android.util.Log
import za.co.dvt.battlebase.common.data.local.common.DatabaseResponse
import za.co.dvt.battlebase.common.data.local.mapper.PokemonMapper
import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.features.home.data.local.dataSource.PokemonLocalDataSource
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {
    override suspend fun savePokemonList(pokemonList: List<Pokemon>): Response<String> {
        try {
            return when (val databaseResponse = pokemonLocalDataSource.savePokemonList(PokemonMapper.mapToPokemonWithAbilitiesAndStatsList(pokemonList))) {
                is DatabaseResponse.Error<String> -> {
                    Response.Error(databaseResponse.message)
                }

                is DatabaseResponse.Success<String> -> {
                    Response.Success(databaseResponse.data)
                }
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Response.Error(e.message ?: "")
        }
    }

    override suspend fun savePokemon(pokemon: Pokemon): Response<String> {
        try {
            return when (val databaseResponse = pokemonLocalDataSource.savePokemon(PokemonMapper.mapToPokemonWithAbilitiesAndStats(pokemon))) {
                is DatabaseResponse.Error<String> -> {
                    Response.Error(databaseResponse.message)
                }

                is DatabaseResponse.Success<String> -> {
                    Response.Success(databaseResponse.data)
                }
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Response.Error(e.message ?: "")
        }
    }
}