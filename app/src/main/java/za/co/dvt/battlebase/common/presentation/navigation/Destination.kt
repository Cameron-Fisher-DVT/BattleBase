package za.co.dvt.battlebase.common.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Destination : NavKey {
    @Serializable
    data object HomeScreen : Destination()

    @Serializable
    data object MenuScreen : Destination()

    @Serializable
    data class HomeInformationScreen(val pokemonId: String) : Destination()
}