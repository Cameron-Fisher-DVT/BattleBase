package za.co.dvt.battlebase.features.menu.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.navigation3.runtime.NavBackStack
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination

class MenuScreenViewModel(
    private val navBackStack: NavBackStack<Destination>
) : BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false)

    fun onNavigateUp() {
        navBackStack.removeLastOrNull()
    }
}