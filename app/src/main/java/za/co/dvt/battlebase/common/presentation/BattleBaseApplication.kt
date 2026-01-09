package za.co.dvt.battlebase.common.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import za.co.dvt.battlebase.common.presentation.di.commonModules.presentationModule
import za.co.dvt.battlebase.common.presentation.di.featureModules.homeModule
import za.co.dvt.battlebase.common.presentation.di.featureModules.menuModule

class BattleBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BattleBaseApplication)
            androidLogger()
            modules(presentationModule, homeModule, menuModule)
        }
    }
}