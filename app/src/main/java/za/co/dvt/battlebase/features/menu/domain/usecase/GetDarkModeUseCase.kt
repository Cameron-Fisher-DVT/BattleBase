package za.co.dvt.battlebase.features.menu.domain.usecase

import za.co.dvt.battlebase.features.menu.domain.repository.MenuManagementRepository

class GetDarkModeUseCase(
    private val menuManagementRepository: MenuManagementRepository
) {
    suspend operator fun invoke(): Boolean = menuManagementRepository.getDarkMode()
}