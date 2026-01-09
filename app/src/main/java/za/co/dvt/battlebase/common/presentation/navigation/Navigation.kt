package za.co.dvt.battlebase.common.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.androidx.compose.koinViewModel
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.Stat
import za.co.dvt.battlebase.features.home.presentation.HomeInformationScreen
import za.co.dvt.battlebase.features.home.presentation.HomeInformationScreenViewModel
import za.co.dvt.battlebase.features.home.presentation.HomeScreen
import za.co.dvt.battlebase.features.home.presentation.HomeScreenViewModel
import za.co.dvt.battlebase.features.menu.presentation.MenuScreen
import za.co.dvt.battlebase.features.menu.presentation.MenuScreenViewModel

@Composable
fun Navigation(
    navBackStack: NavBackStack<Destination>,
    onDarkModeToggled: (isDarkMode: Boolean) -> Unit
) {
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
                    pokemonListUiState = homeScreenViewModel.pokemonListUiState,
                    loadingIndicatorState = homeScreenViewModel.loadingIndicatorState,
                    isDarkModeState = homeScreenViewModel.isDarkModeMutableState,
                    onInit = {
                        homeScreenViewModel.onInit()
                    },
                    onDarkModeChecked = { isDarkMode ->
                        onDarkModeToggled(isDarkMode)
                    },
                    onMenuClicked = {
                        homeScreenViewModel.navigateToMenuScreen()
                    }
                ) { pokemon ->
                    homeScreenViewModel.navigateToHomeInformationScreen(pokemon)
                }
            }

            entry<Destination.MenuScreen> {
                val menuScreenViewModel = koinViewModel<MenuScreenViewModel>()
                MenuScreen(
                    isDarkModeState = menuScreenViewModel.isDarkModeMutableState,
                    onDarkModeToggled = { isDarkMode ->
                        onDarkModeToggled(isDarkMode)
                        menuScreenViewModel.saveDarkMode(isDarkMode)
                    }
                ) {
                    menuScreenViewModel.onNavigateUp()
                }
            }

            entry<Destination.HomeInformationScreen> { key ->
                val homeInformationScreenViewModel = koinViewModel<HomeInformationScreenViewModel>()
                homeInformationScreenViewModel.selectedPokemon.value = key.pokemon
                HomeInformationScreen(
                    pokemon = homeInformationScreenViewModel.selectedPokemon,
                    snackbarHostState = homeInformationScreenViewModel.snackbarHostState,
                    onNavigateUpClicked = {
                        homeInformationScreenViewModel.onNavigateUp()
                    },
                    onFavoriteClicked = { pokemon ->
                        homeInformationScreenViewModel.selectedPokemon.value = pokemon
                        homeInformationScreenViewModel.savePokemon(pokemon)
                    }
                )
            }
        }
    )
}