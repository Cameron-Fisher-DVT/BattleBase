package za.co.dvt.battlebase.features.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination

class HomeScreenViewModel(
    private val navBackStack: NavBackStack<Destination>
) : BaseViewModel() {
    private val loadingIndicatorMutableState = mutableStateOf(false)
    val loadingIndicatorState: State<Boolean> = loadingIndicatorMutableState

    fun navigateToMenuScreen() = viewModelScope.launch {
        navBackStack.add(Destination.MenuScreen)
    }
}