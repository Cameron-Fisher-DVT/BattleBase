package za.co.dvt.battlebase.features.home.domain.usecase

import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.repository.PokemonRepository

class FetchPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(offset: Int, limit: Int): Response<List<Pokemon>> {
        return pokemonRepository.fetchPokemonList(offset, limit)
    }
}