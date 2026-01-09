package za.co.dvt.battlebase.features.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import kotlinx.coroutines.launch
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.Stat
import za.co.dvt.battlebase.features.home.domain.usecase.SavePokemonUseCase

class HomeInformationScreenViewModel(
    private val navBackStack: NavBackStack<Destination>,
    private val savePokemonUseCase: SavePokemonUseCase,
) : BaseViewModel() {
    val selectedPokemon = mutableStateOf(Pokemon())

    fun onNavigateUp() {
        navBackStack.removeLastOrNull()
    }

    fun savePokemon(pokemon: Pokemon) = viewModelScope.launch {
        savePokemonUseCase(pokemon)
    }
}