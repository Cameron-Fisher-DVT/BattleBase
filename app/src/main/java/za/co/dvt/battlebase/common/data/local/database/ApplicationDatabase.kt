package za.co.dvt.battlebase.common.data.local.database

import za.co.dvt.battlebase.common.data.local.database.dao.AbilityDao
import za.co.dvt.battlebase.common.data.local.database.dao.PokemonDao
import za.co.dvt.battlebase.common.data.local.database.dao.StatDao

interface ApplicationDatabase {
    companion object {
        const val INVALID_OPERATION = -1L
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ApplicationDatabase"
        const val DATABASE_EXPORT_SCHEMA = true
    }

    fun pokemonDao(): PokemonDao
    fun abilityDao(): AbilityDao
    fun statDao(): StatDao
}