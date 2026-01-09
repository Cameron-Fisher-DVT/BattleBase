package za.co.dvt.battlebase.common.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["pokemonId"])
    ], tableName = "Ability",
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class AbilityEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val pokemonId: String
)