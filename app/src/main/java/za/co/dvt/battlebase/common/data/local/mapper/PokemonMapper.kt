package za.co.dvt.battlebase.common.data.local.mapper

import za.co.dvt.battlebase.common.data.local.model.AbilityEntity
import za.co.dvt.battlebase.common.data.local.model.PokemonEntity
import za.co.dvt.battlebase.common.data.local.model.PokemonWithAbilitiesAndStats
import za.co.dvt.battlebase.common.data.local.model.StatEntity
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.Stat

object PokemonMapper {
    fun mapToPokemonEntity(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            isFavourite = pokemon.isFavourite
        )
    }

    fun mapToPokemonEntityList(pokemonList: List<Pokemon>): List<PokemonEntity> {
        return pokemonList.map { mapToPokemonEntity(it) }
    }

    fun mapToPokemon(pokemonWithAbilitiesAndStats: PokemonWithAbilitiesAndStats): Pokemon {
        return Pokemon(
            id = pokemonWithAbilitiesAndStats.pokemon.id,
            name = pokemonWithAbilitiesAndStats.pokemon.name,
            imageUrl = pokemonWithAbilitiesAndStats.pokemon.imageUrl,
            abilityList = mapToAbilityList(pokemonWithAbilitiesAndStats.abilityList),
            statsList = mapToStatList(pokemonWithAbilitiesAndStats.statList),
            isFavourite = pokemonWithAbilitiesAndStats.pokemon.isFavourite
        )
    }

    fun mapToPokemonList(pokemonWithAbilitiesAndStatsList: List<PokemonWithAbilitiesAndStats>): List<Pokemon> {
        return pokemonWithAbilitiesAndStatsList.map { mapToPokemon(it) }
    }

    fun mapToAbility(abilityEntity: AbilityEntity): Ability {
        return Ability(
            id = abilityEntity.id,
            name = abilityEntity.name
        )
    }

    fun mapToAbilityList(abilityEntityList: List<AbilityEntity>): List<Ability> {
        return abilityEntityList.map { mapToAbility(it) }
    }

    fun mapToAbilityEntity(ability: Ability, pokemonId: String): AbilityEntity {
        return AbilityEntity(
            id = ability.id,
            name = ability.name,
            pokemonId = pokemonId
        )
    }

    fun mapToStatEntity(stat: Stat, pokemonId: String): StatEntity {
        return StatEntity(
            id = stat.id,
            score = stat.score,
            name = stat.name,
            pokemonId = pokemonId
        )
    }

    fun mapToStat(statEntity: StatEntity): Stat {
        return Stat(
            id = statEntity.id,
            score = statEntity.score,
            name = statEntity.name
        )
    }

    fun mapToStatList(statEntityList: List<StatEntity>): List<Stat> {
        return statEntityList.map { mapToStat(it) }
    }

    fun mapToPokemonWithAbilitiesAndStats(pokemon: Pokemon): PokemonWithAbilitiesAndStats {
        return PokemonWithAbilitiesAndStats(
            pokemon = PokemonEntity(
                id = pokemon.id,
                name = pokemon.name,
                imageUrl = pokemon.imageUrl,
                isFavourite = pokemon.isFavourite
            ),
            abilityList = pokemon.abilityList.map { ability ->
                mapToAbilityEntity(ability, pokemon.id)
            },
            statList = pokemon.statsList.map { stat ->
                mapToStatEntity(stat, pokemon.id)
            }
        )
    }

    fun mapToPokemonWithAbilitiesAndStatsList(pokemonList: List<Pokemon>): List<PokemonWithAbilitiesAndStats> {
        return pokemonList.map { pokemon ->
            PokemonWithAbilitiesAndStats(
                pokemon = PokemonEntity(
                    id = pokemon.id,
                    name = pokemon.name,
                    imageUrl = pokemon.imageUrl,
                    isFavourite = pokemon.isFavourite
                ),
                abilityList = pokemon.abilityList.map { ability ->
                    mapToAbilityEntity(ability, pokemon.id)
                },
                statList = pokemon.statsList.map { stat ->
                    mapToStatEntity(stat, pokemon.id)
                }
            )
        }
    }
}