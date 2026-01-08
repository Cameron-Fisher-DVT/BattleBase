package za.co.dvt.battlebase.common.presentation.di.commonModules

import androidx.navigation3.runtime.NavBackStack
import org.koin.dsl.module
import za.co.dvt.battlebase.common.presentation.navigation.Destination

val presentationModule = module {
    single {
        NavBackStack<Destination>(Destination.HomeScreen)
    }
}