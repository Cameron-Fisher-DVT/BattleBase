package za.co.dvt.battlebase.features.menu.domain.usecase

import za.co.dvt.battlebase.features.menu.domain.repository.MenuManagementRepository

class SaveDarkModeUseCase(
    private val menuManagementRepository: MenuManagementRepository
) {
    suspend operator fun invoke(isDarkMode: Boolean) {
        menuManagementRepository.saveDarkMode(isDarkMode)
    }
}