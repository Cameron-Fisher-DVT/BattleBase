package za.co.dvt.battlebase.common.presentation.navigation

import androidx.compose.runtime.Composable
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
                ) { itemId ->
                    homeScreenViewModel.navigateToHomeInformationScreen(itemId)
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

                HomeInformationScreen( //TODO: Fetch Pokemon from backend by ID
                    pokemon = Pokemon(
                        pokemonId = "ID",
                        name = "Pika",
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/44.png",
                        abilityList = listOf(
                            Ability("ID", "Ability A"),
                            Ability("ID", "Ability B"),
                            Ability("ID", "Ability C"),
                            Ability("ID", "Ability D"),
                            Ability("ID", "Ability E"),
                            Ability("ID", "Ability F"),
                            Ability("ID", "Ability G")

                        ),
                        statsList = listOf(
                            Stat(10, "Stat A"),
                            Stat(10, "Stat B"),
                            Stat(10, "Stat C"),
                            Stat(10, "Stat D"),
                            Stat(10, "Stat E"),
                            Stat(10, "Stat F"),
                            Stat(10, "Stat G"),
                            Stat(10, "Stat H")

                        ),
                        isFavourite = false
                    ),
                    snackbarHostState = homeInformationScreenViewModel.snackbarHostState,
                    onNavigateUpClicked = {
                        homeInformationScreenViewModel.onNavigateUp()
                    },
                    onFavoriteClicked = {
                        //TODO: Cache pokemon or remove from cache
                    }
                )
            }
        }
    )
}