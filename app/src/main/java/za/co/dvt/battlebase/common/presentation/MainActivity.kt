package za.co.dvt.battlebase.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import za.co.dvt.battlebase.common.presentation.ui.theme.BattleBaseTheme
import za.co.dvt.battlebase.features.home.presentation.HomeScreen
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BattleBaseTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    val homeScreenViewModel = HomeScreenViewModel()
                    HomeScreen(modifier = Modifier.padding(innerPadding), snackbarHostState = homeScreenViewModel.snackbarHostState) {
                        homeScreenViewModel.displaySnackbar("Menu")
                    }
                }
            }
        }
    }
}