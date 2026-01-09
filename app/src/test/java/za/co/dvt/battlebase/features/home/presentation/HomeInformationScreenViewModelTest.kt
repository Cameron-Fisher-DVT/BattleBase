package za.co.dvt.battlebase.features.home.presentation

import androidx.navigation3.runtime.NavBackStack
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.dvt.battlebase.common.domain.common.Response
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.Stat
import za.co.dvt.battlebase.features.home.domain.usecase.SavePokemonUseCase

class HomeInformationScreenViewModelTest {
    @MockK
    private lateinit var navBackStack: NavBackStack<Destination>

    @MockK
    private lateinit var savePokemonUseCase: SavePokemonUseCase

    @InjectMockKs
    private lateinit var sut: HomeInformationScreenViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)


    }

    @Test
    @DisplayName("Should Save Pokemon When Save Pokemon Is Called")
    fun shouldSavePokemonWhenSavePokemonIsCalled() {
        val pokemon = Pokemon(
            id = "7",
            name = "Pika",
            isFavourite = false,
            abilityList = listOf(Ability("lightning")),
            statsList = listOf(Stat("hp", 45)),

        )

        coEvery { savePokemonUseCase(pokemon) } returns Response.Success("saved")

        sut.savePokemon(pokemon)

        coVerify { savePokemonUseCase(pokemon) }
    }
}