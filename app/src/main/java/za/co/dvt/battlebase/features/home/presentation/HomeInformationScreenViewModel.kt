package za.co.dvt.battlebase.features.home.presentation

import androidx.navigation3.runtime.NavBackStack
import za.co.dvt.battlebase.common.presentation.BaseViewModel
import za.co.dvt.battlebase.common.presentation.navigation.Destination

class HomeInformationScreenViewModel(
    private val navBackStack: NavBackStack<Destination>
) : BaseViewModel() {
    fun onNavigateUp() {
        navBackStack.removeLastOrNull()
    }
}