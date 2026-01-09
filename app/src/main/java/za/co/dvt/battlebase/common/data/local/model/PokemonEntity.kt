package za.co.dvt.battlebase.common.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)], tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val isFavourite: Boolean,
)