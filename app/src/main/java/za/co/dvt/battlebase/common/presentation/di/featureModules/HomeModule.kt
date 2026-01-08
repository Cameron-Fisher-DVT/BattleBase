package za.co.dvt.battlebase.common.presentation.di.featureModules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import za.co.dvt.battlebase.features.home.presentation.HomeInformationScreenViewModel
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel

val homeModule = module {
    viewModel {
        HomeScreenViewModel(get())
    }
    viewModel {
        HomeInformationScreenViewModel(get())
    }
}