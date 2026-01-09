package za.co.dvt.battlebase.features.home.data.remote.dataSource

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.dvt.battlebase.common.data.remote.common.ApiResponse
import za.co.dvt.battlebase.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.battlebase.features.home.data.remote.adapter.PokeApiServiceAdapter
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.battlebase.features.home.data.remote.api.model.pokemon.dto.PokemonDto

class PokemonRemoteDataSourceImplTest {
    @MockK
    private lateinit var pokeApiServiceAdapter: PokeApiServiceAdapter

    @InjectMockKs
    private lateinit var sut: PokemonRemoteDataSourceImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private val mockPokemonResponse = PokemonResponse(
        count = 3456,
        next = "",
        previous = null,
        results = listOf(
            PokemonDto(
                name = "Pika",
                url = "https://pokeapi.co/api/v2/pokemon/45/"
            )
        )
    )

    @Test
    @DisplayName("Return Success Whenever Fetch Pokemon List Is Called")
    fun returnSuccessWheneverFetchPokemonListIsCalled() = runTest {
        coEvery { pokeApiServiceAdapter.fetchPokemonList(any<Int>(), any<Int>()) } returns NetworkResponse.Success(mockPokemonResponse)

        val result = sut.fetchPokemonList(0, 20)

        assertTrue(result is ApiResponse.Success)
        result as ApiResponse.Success
        assertEquals(3456, result.data.count)
        assertEquals(mockPokemonResponse, result.data)
        assertEquals("Pika", result.data.results[0].name)
    }

    @Test
    @DisplayName("Return Network Error Whenever Fetch Pokemon List Response Fails")
    fun returnNetworkErrorWheneverFetchPokemonListResponseFails() = runTest {
        val errorMessage = "Network error"
        coEvery { pokeApiServiceAdapter.fetchPokemonList(any<Int>(), any<Int>()) } returns NetworkResponse.NetworkError(Exception(errorMessage))

        val result = sut.fetchPokemonList(0, 20)

        assertTrue(result is ApiResponse.Error)
        assertEquals(errorMessage, (result as ApiResponse.Error).message)
    }
}