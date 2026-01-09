package za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation

import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto.PokeAbilityDto
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto.SpriteDto
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto.StatsDto

data class PokemonInformationResponse(
    val abilities: List<PokeAbilityDto>,
    val sprites: SpriteDto,
    val stats: List<StatsDto>
)