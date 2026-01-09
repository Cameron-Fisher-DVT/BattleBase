package za.co.dvt.battlebase.features.menu.presentation

import androidx.navigation3.runtime.NavBackStack
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.dvt.battlebase.common.presentation.navigation.Destination
import za.co.dvt.battlebase.features.menu.domain.usecase.GetDarkModeUseCase
import za.co.dvt.battlebase.features.menu.domain.usecase.SaveDarkModeUseCase

class MenuScreenViewModelTest {
    @MockK
    private lateinit var navBackStack: NavBackStack<Destination>

    @MockK
    private lateinit var saveDarkModeUseCase: SaveDarkModeUseCase

    @MockK
    private lateinit var getDarkModeUseCase: GetDarkModeUseCase

    @InjectMockKs
    private lateinit var sut: MenuScreenViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    @DisplayName("Should Save Dark Mode When Save Dark UseCase Mode Is Called")
    fun shouldSaveDarkModeWhenSaveDarkModeUseCaseIsCalled() {
        coEvery { saveDarkModeUseCase(any<Boolean>()) } returns Unit

        sut.saveDarkMode(true)

        coVerify { saveDarkModeUseCase(any<Boolean>()) }
    }
}