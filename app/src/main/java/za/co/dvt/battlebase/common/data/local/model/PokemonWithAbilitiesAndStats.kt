package za.co.dvt.battlebase.common.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

class PokemonWithAbilitiesAndStats(
    @Embedded
    val pokemon: PokemonEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val abilityList: List<AbilityEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val statList: List<StatEntity>
)