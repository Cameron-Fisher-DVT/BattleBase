package za.co.dvt.battlebase.features.menu.data.repository

import za.co.dvt.battlebase.features.menu.data.local.dataSource.MenuManagementLocalDataSource
import za.co.dvt.battlebase.features.menu.domain.repository.MenuManagementRepository

class MenuManagementRepositoryImpl(
    private val menuManagementLocalDataSource: MenuManagementLocalDataSource
): MenuManagementRepository {
    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        menuManagementLocalDataSource.saveDarkMode(isDarkMode)
    }

    override suspend fun getDarkMode(): Boolean {
        return menuManagementLocalDataSource.getDarkMode()
    }
}