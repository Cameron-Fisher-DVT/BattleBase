package za.co.dvt.battlebase.common.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.androidx.compose.koinViewModel
import za.co.dvt.battlebase.features.home.presentation.HomeScreen
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel
import za.co.dvt.battlebase.features.menu.MenuScreen

@Composable
fun Navigation(
    navBackStack: NavBackStack<Destination>
) {
    //TODO: [03] Menu - Add ability to change dark/light mode
    NavDisplay(
        backStack = navBackStack,
        onBack = { navBackStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Destination.HomeScreen> {
                val homeScreenViewModel = koinViewModel<HomeScreenViewModel>()
                HomeScreen(snackbarHostState = homeScreenViewModel.snackbarHostState) {
                    homeScreenViewModel.navigateToMenuScreen()
                }
            }

            entry<Destination.MenuScreen> {
                MenuScreen()
            }
        }
    )
}