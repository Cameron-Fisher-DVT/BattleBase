package za.co.dvt.battlebase.features.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.Stat
import za.co.dvt.battlebase.features.home.domain.usecase.SavePokemonListUseCase
import za.co.dvt.battlebase.features.menu.domain.usecase.GetDarkModeUseCase

class HomeScreenViewModel(
    private val navBackStack: NavBackStack<Destination>,
    private val savePokemonListUseCase: SavePokemonListUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase
) : BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false)

    private val loadingIndicatorMutableState = mutableStateOf(false)
    val loadingIndicatorState: State<Boolean> = loadingIndicatorMutableState

    init {
        onInit()
    }

    fun onInit() {
        isDarkMode()
    }

    fun navigateToMenuScreen() = viewModelScope.launch {
        navBackStack.add(Destination.MenuScreen)
    }

    fun navigateToHomeInformationScreen(pokemonId: String) = viewModelScope.launch {
        navBackStack.add(Destination.HomeInformationScreen(pokemonId))
    }

    fun isDarkMode() = viewModelScope.launch {
        isDarkModeMutableState.value = getDarkModeUseCase()
    }

    fun savePokemonList(pokemonList: List<Pokemon>) = viewModelScope.launch {
        savePokemonListUseCase(pokemonList)
    }
}