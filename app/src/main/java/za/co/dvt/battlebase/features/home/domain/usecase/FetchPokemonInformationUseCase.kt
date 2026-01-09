package za.co.dvt.battlebase.features.home.domain.usecase

import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.features.home.domain.model.PokemonInformation
import za.co.dvt.battlebase.features.home.domain.repository.PokemonRepository

class FetchPokemonInformationUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: String): Response<PokemonInformation> {
        return pokemonRepository.fetchPokemonInformation(pokemonId)
    }
}