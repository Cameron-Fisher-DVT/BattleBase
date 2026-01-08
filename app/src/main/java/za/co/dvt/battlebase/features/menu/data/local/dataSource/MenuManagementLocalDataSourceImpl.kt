package za.co.dvt.battlebase.features.menu.data.local.dataSource

import za.co.dvt.battlebase.common.data.preferences.PreferencesManager

class MenuManagementLocalDataSourceImpl(
    private val preferencesManager: PreferencesManager
) : MenuManagementLocalDataSource {
    private companion object {
        const val DARK_MODE_KEY = "dark_mode"
    }

    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        preferencesManager.saveBoolean(DARK_MODE_KEY, isDarkMode)
    }

    override suspend fun getDarkMode(): Boolean = preferencesManager.getBoolean(DARK_MODE_KEY)
}