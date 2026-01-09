package za.co.dvt.battlebase.features.home.data.remote.mapper

import za.co.dvt.battlebase.common.domain.extensions.extractIdFromUrl
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.PokemonInformationResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto.PokeAbilityDto
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto.StatDto
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemonInformation.dto.StatsDto
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.PokemonInformation
import za.co.dvt.battlebase.features.home.domain.model.Stat

object RemotePokemonMapper {
    fun mapToPokemonList(pokemonResponse: PokemonResponse): List<Pokemon> {
        return pokemonResponse.results.map {
            Pokemon(
                name = it.name,
                id = it.url.extractIdFromUrl()
            )
        }
    }

    fun mapToPokemonInformation(pokemonInformationResponse: PokemonInformationResponse): PokemonInformation {
        return PokemonInformation(
            frontDefaultSprite = pokemonInformationResponse.sprites.front_default,
            pokemonAbilityList = mapToAbilityList(pokemonInformationResponse.abilities),
            stats = mapToStatsList(pokemonInformationResponse.stats)
        )
    }

    fun mapToAbility(pokeAbilityDto: PokeAbilityDto): Ability {
        return Ability(
            id = pokeAbilityDto.ability.url.extractIdFromUrl(),
            name = pokeAbilityDto.ability.name
        )
    }

    fun mapToAbilityList(pokeAbilityDtoList: List<PokeAbilityDto>): List<Ability> {
        return pokeAbilityDtoList.map { mapToAbility(it) }
    }

    fun mapToStats(statsDto: StatsDto): Stat {
        return Stat(
            score = statsDto.base_stat,
            name = statsDto.stat.name
        )
    }

    fun mapToStatsList(statsDtoList: List<StatsDto>): List<Stat> {
        return statsDtoList.map { mapToStats(it) }
    }
}