package za.co.dvt.battlebase.features.menu.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import kotlinx.coroutines.launch
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.features.menu.domain.usecase.GetDarkModeUseCase
import za.co.dvt.battlebase.features.menu.domain.usecase.SaveDarkModeUseCase

class MenuScreenViewModel(
    private val navBackStack: NavBackStack<Destination>,
    private val saveDarkModeUseCase: SaveDarkModeUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase
) : BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false)

    init {
        viewModelScope.launch {
            isDarkModeMutableState.value = getDarkModeUseCase()
        }
    }

    fun onNavigateUp() {
        navBackStack.removeLastOrNull()
    }

    fun saveDarkMode(isDarkMode: Boolean) = viewModelScope.launch {
        saveDarkModeUseCase(isDarkMode)
    }
}