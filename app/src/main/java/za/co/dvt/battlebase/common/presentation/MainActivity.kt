package za.co.dvt.battlebase.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import org.koin.android.ext.android.get
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.common.presentation.navigation.Navigation
import za.co.dvt.battlebase.common.presentation.ui.theme.BattleBaseTheme
import za.co.dvt.battlebase.features.home.presentation.HomeScreen
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme = remember { mutableStateOf(false) }
            BattleBaseTheme(darkTheme = darkTheme.value) {
                Navigation(get()) { isDarkMode ->
                    darkTheme.value = isDarkMode
                }
            }
        }
    }
}