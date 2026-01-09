package za.co.dvt.battlebase.features.home.data.repository

import android.util.Log
import za.co.dvt.battlebase.common.data.local.common.DatabaseResponse
import za.co.dvt.battlebase.common.data.local.mapper.LocalPokemonMapper
import za.co.dvt.battlebase.common.data.local.model.PokemonWithAbilitiesAndStats
import za.co.dvt.battlebase.common.data.remote.common.ApiResponse
import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.features.home.data.local.dataSource.PokemonLocalDataSource
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse
import za.co.dvt.battlebase.features.home.data.remote.dataSource.PokemonRemoteDataSource
import za.co.dvt.battlebase.features.home.data.remote.mapper.RemotePokemonMapper
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.PokemonInformation
import za.co.dvt.battlebase.features.home.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun savePokemonList(pokemonList: List<Pokemon>): Response<String> {
        try {
            return when (val databaseResponse = pokemonLocalDataSource.savePokemonList(LocalPokemonMapper.mapToPokemonWithAbilitiesAndStatsList(pokemonList))) {
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
            return when (val databaseResponse = pokemonLocalDataSource.savePokemon(LocalPokemonMapper.mapToPokemonWithAbilitiesAndStats(pokemon))) {
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

    override suspend fun fetchPokemonList(offset: Int, limit: Int): Response<List<Pokemon>> {
        try {
            return when (val databaseResponse = pokemonLocalDataSource.fetchAllPokemonWithAbilitiesAndStats(offset, limit)) {
                is DatabaseResponse.Error<List<PokemonWithAbilitiesAndStats>> -> {
                    fetchRemotePokemonList(offset, limit)
                }

                is DatabaseResponse.Success<List<PokemonWithAbilitiesAndStats>> -> {
                    if (databaseResponse.data.size >= 20) {
                        Response.Success(LocalPokemonMapper.mapToPokemonList(databaseResponse.data))
                    } else {
                        fetchRemotePokemonList(offset, limit)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Response.Error(e.message ?: "")
        }
    }

    private suspend fun fetchRemotePokemonList(offset: Int, limit: Int): Response<List<Pokemon>> {
        try {
            return when (val apiResponse = pokemonRemoteDataSource.fetchPokemonList(offset, limit)) {
                is ApiResponse.Success<PokemonResponse> -> {
                    Response.Success(RemotePokemonMapper.mapToPokemonList(apiResponse.data))
                }

                is ApiResponse.Error<PokemonResponse> -> {
                    Response.Error(apiResponse.message)
                }
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Response.Error(e.message ?: "")
        }
    }

    override suspend fun fetchPokemonInformation(pokemonId: String): Response<PokemonInformation> {
        try {
            return when (val apiResponse = pokemonRemoteDataSource.fetchPokemonInformationResponse(pokemonId)) {
                is ApiResponse.Success<PokemonInformationResponse> -> {
                    Response.Success(RemotePokemonMapper.mapToPokemonInformation(apiResponse.data))
                }

                is ApiResponse.Error<PokemonInformationResponse> -> {
                    Response.Error(apiResponse.message)
                }
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Response.Error(e.message ?: "")
        }
    }
}