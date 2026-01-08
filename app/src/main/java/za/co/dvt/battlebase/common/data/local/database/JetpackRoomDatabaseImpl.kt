package za.co.dvt.battlebase.common.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import za.co.dvt.battlebase.common.data.local.database.ApplicationDatabase.Companion.DATABASE_EXPORT_SCHEMA
import za.co.dvt.battlebase.common.data.local.database.ApplicationDatabase.Companion.DATABASE_NAME
import za.co.dvt.battlebase.common.data.local.database.ApplicationDatabase.Companion.DATABASE_VERSION
import za.co.dvt.battlebase.common.data.local.database.dao.AbilityDao
import za.co.dvt.battlebase.common.data.local.database.dao.PokemonDao
import za.co.dvt.battlebase.common.data.local.database.dao.StatDao
import za.co.dvt.battlebase.common.data.local.model.AbilityEntity
import za.co.dvt.battlebase.common.data.local.model.PokemonEntity
import za.co.dvt.battlebase.common.data.local.model.StatEntity

@Database(
    entities = [PokemonEntity::class, AbilityEntity::class, StatEntity::class],
    version = DATABASE_VERSION,
    exportSchema = DATABASE_EXPORT_SCHEMA
)
abstract class JetpackRoomDatabaseImpl : RoomDatabase(), ApplicationDatabase {
    abstract override fun pokemonDao(): PokemonDao
    abstract override fun abilityDao(): AbilityDao
    abstract override fun statDao(): StatDao

    companion object {
        @Volatile
        private var INSTANCE: JetpackRoomDatabaseImpl? = null

        fun getDatabase(context: Context): JetpackRoomDatabaseImpl {
            val temporaryInstance = INSTANCE

            if (temporaryInstance != null) {
                return temporaryInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        JetpackRoomDatabaseImpl::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}