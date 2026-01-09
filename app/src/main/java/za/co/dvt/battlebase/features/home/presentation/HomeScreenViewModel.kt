package za.co.dvt.battlebase.features.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import kotlinx.coroutines.async
import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.PokemonInformation
import za.co.dvt.battlebase.features.home.domain.model.Stat
import za.co.dvt.battlebase.features.home.domain.usecase.FetchPokemonInformationUseCase
import za.co.dvt.battlebase.features.home.domain.usecase.FetchPokemonListUseCase
import za.co.dvt.battlebase.features.home.domain.usecase.SavePokemonListUseCase
import za.co.dvt.battlebase.features.menu.domain.usecase.GetDarkModeUseCase

class HomeScreenViewModel(
    private val navBackStack: NavBackStack<Destination>,
    private val savePokemonListUseCase: SavePokemonListUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val fetchPokemonListUseCase: FetchPokemonListUseCase,
    private val fetchPokemonInformationUseCase: FetchPokemonInformationUseCase
) : BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false)

    private val loadingIndicatorMutableState = mutableStateOf(false)
    val loadingIndicatorState: State<Boolean> = loadingIndicatorMutableState

    data class PokemonListUiState(
        val pokemonList: List<Pokemon> = emptyList(),
        val errorMessage: String = ""
    )

    private val pokemonListMutableState = mutableStateOf(PokemonListUiState())
    val pokemonListUiState: State<PokemonListUiState> = pokemonListMutableState

    init {
        onInit()
    }

    fun onInit() {
        isDarkMode()
        fetchPokemonList()
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

    private fun displayLoadingIndicator(shouldDisplay: Boolean = true) {
        loadingIndicatorMutableState.value = shouldDisplay
    }

    fun fetchPokemonList() = viewModelScope.launch {
        displayLoadingIndicator()
        val result = fetchPokemonListUseCase(0, 20)
        when (result) {
            is Response.Error -> {
                displayLoadingIndicator(false)
                pokemonListMutableState.value = PokemonListUiState(errorMessage = result.message)
            }

            is Response.Success<List<Pokemon>> -> {
                displayLoadingIndicator(false)
                fetchPokemonInformationList(result.data)
            }
        }
    }

    fun fetchPokemonInformationList(pokemonList: List<Pokemon>) = viewModelScope.launch {
        val deferredResults = pokemonList.associateWith { pokemon ->
            async {
                fetchPokemonInformationUseCase(pokemon.id)
            }
        }

        val combinedResults = deferredResults.mapValues { (_, deferred) ->
            deferred.await()
        }

        val successfulResults = combinedResults
            .filter { (_, result) -> result is Response.Success }
            .map { (pokemon, result) ->
                val pokemonInformation = (result as Response.Success<PokemonInformation>).data
                pokemon.copy(
                    imageUrl = pokemonInformation.frontDefaultSprite,
                    statsList = pokemonInformation.stats
                )
            }

        savePokemonListUseCase(successfulResults)

        displayLoadingIndicator(false)
        pokemonListMutableState.value = PokemonListUiState(pokemonList = successfulResults)
    }
}