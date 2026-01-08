package za.co.dvt.battlebase.common.data.preferences.datastore

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import za.co.dvt.battlebase.common.data.preferences.PreferencesManager

class JetpackDataStoreManagerImpl(
    private val application: Application
) : PreferencesManager {
    private val Application.dataStore by preferencesDataStore("BattleBase")

    override suspend fun saveBoolean(key: String, value: Boolean) {
        application.dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean = application.dataStore.data.map {
        it[booleanPreferencesKey(key)]
    }.firstOrNull() ?: false
}