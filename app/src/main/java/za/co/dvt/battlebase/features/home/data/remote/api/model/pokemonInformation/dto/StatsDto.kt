package za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto

data class StatsDto(
    val base_stat: Int = 0,
    val stat: StatDto = StatDto()
)