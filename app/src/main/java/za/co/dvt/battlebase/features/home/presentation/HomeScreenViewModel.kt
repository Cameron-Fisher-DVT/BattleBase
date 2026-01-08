package za.co.dvt.battlebase.features.home.presentation

import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import kotlinx.coroutines.launch
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination

class HomeScreenViewModel(
    private val navBackStack: NavBackStack<Destination>
) : BaseViewModel() {
    fun navigateToMenuScreen() = viewModelScope.launch {
        navBackStack.add(Destination.MenuScreen)
    }
}