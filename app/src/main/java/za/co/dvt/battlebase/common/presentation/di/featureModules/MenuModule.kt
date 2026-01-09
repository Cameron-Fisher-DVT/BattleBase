package za.co.dvt.battlebase.common.presentation.di.featureModules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import za.co.dvt.battlebase.features.menu.data.local.dataSource.MenuManagementLocalDataSource
import za.co.dvt.battlebase.features.menu.data.local.dataSource.MenuManagementLocalDataSourceImpl
import za.co.dvt.battlebase.features.menu.data.repository.MenuManagementRepositoryImpl
import za.co.dvt.battlebase.features.menu.domain.repository.MenuManagementRepository
import za.co.dvt.battlebase.features.menu.domain.usecase.GetDarkModeUseCase
import za.co.dvt.battlebase.features.menu.domain.usecase.SaveDarkModeUseCase
import za.co.dvt.battlebase.features.menu.presentation.MenuScreenViewModel

val menuModule = module {
    factory<MenuManagementLocalDataSource> { MenuManagementLocalDataSourceImpl(get()) }
    factory<MenuManagementRepository> { MenuManagementRepositoryImpl(get()) }
    factory { GetDarkModeUseCase(get()) }
    factory { SaveDarkModeUseCase(get()) }
    viewModel {
        MenuScreenViewModel(
            get(),
            get(),
            get()
        )
    }
}