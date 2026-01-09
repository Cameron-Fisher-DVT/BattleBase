package za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon

import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.dto.PokemonDto

data class PokemonResponse(
    val count: Int = 0,
    val next: String = "",
    val previous: String? = null,
    val results: List<PokemonDto>
)