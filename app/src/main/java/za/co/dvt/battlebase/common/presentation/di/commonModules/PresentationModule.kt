package za.co.dvt.battlebase.common.presentation.di.commonModules

import androidx.navigation3.runtime.NavBackStack
import org.koin.dsl.module
import za.co.dvt.battlebase.common.data.local.database.ApplicationDatabase
import za.co.dvt.battlebase.common.data.local.database.JetpackRoomDatabaseImpl
import za.co.dvt.battlebase.common.data.local.preferences.PreferencesManager
import za.co.dvt.battlebase.common.data.local.preferences.datastore.JetpackDataStoreManagerImpl
import za.co.dvt.battlebase.common.presentation.manager.resourceManager.ResourceManager
import za.co.dvt.battlebase.common.presentation.navigation.Destination

val presentationModule = module {
    single {
        NavBackStack<Destination>(Destination.HomeScreen)
    }

    single<PreferencesManager> {
        JetpackDataStoreManagerImpl(get())
    }

    single<ApplicationDatabase> {
        JetpackRoomDatabaseImpl.getDatabase(get())
    }

    single {
        ResourceManager(get())
    }
}