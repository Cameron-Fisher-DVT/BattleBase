package za.co.dvt.battlebase.features.home.domain.usecase

import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.repository.PokemonRepository

class SavePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon): Response<String> {
        return pokemonRepository.savePokemon(pokemon)
    }
}