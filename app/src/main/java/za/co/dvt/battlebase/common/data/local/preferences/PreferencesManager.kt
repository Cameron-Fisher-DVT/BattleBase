package za.co.dvt.battlebase.common.data.local.preferences

interface PreferencesManager {
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean
}