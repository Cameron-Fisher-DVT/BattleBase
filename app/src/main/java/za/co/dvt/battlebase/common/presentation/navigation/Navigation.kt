package za.co.dvt.battlebase.common.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.androidx.compose.koinViewModel
import za.co.dvt.battlebase.features.home.presentation.HomeScreen
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel
import za.co.dvt.battlebase.features.menu.presentation.MenuScreen
import za.co.dvt.battlebase.features.menu.presentation.MenuScreenViewModel

@Composable
fun Navigation(
    navBackStack: NavBackStack<Destination>,
    onDarkModeToggled: (isDarkMode: Boolean) -> Unit
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
                HomeScreen(
                    snackbarHostState = homeScreenViewModel.snackbarHostState,
                    loadingIndicatorState = homeScreenViewModel.loadingIndicatorState
                ) {
                    homeScreenViewModel.navigateToMenuScreen()
                }
            }

            entry<Destination.MenuScreen> {
                val menuScreenViewModel = koinViewModel<MenuScreenViewModel>()
                MenuScreen(
                    isDarkModeState = menuScreenViewModel.isDarkModeMutableState,
                    onDarkModeToggled = { isDarkMode ->
                        onDarkModeToggled(isDarkMode)
                        //TODO [17] Jetpack DataStore - Save dark mode state
                    }
                ) {
                    menuScreenViewModel.onNavigateUp()
                }
            }
        }
    )
}