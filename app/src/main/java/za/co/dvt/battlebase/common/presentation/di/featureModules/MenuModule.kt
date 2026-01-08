package za.co.dvt.battlebase.common.presentation.di.featureModules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import za.co.dvt.battlebase.features.menu.presentation.MenuScreenViewModel

val menuModule = module {
    viewModel {
        MenuScreenViewModel(get())
    }
}