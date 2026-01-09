package za.co.dvt.battlebase.features.menu.data.local.dataSource

interface MenuManagementLocalDataSource {
    suspend fun saveDarkMode(isDarkMode: Boolean)
    suspend fun getDarkMode(): Boolean
}