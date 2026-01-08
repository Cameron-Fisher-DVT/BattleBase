package za.co.dvt.battlebase.features.menu.domain.repository

interface MenuManagementRepository {
    suspend fun saveDarkMode(isDarkMode: Boolean)
    suspend fun getDarkMode(): Boolean
}