package za.co.dvt.battlebase.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import za.co.dvt.battlebase.R
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.composeuilib.common.domain.model.Item
import za.co.dvt.composeuilib.features.buttons.CardItemView
import za.co.dvt.composeuilib.features.misc.LoadingIndicatorView
import za.co.dvt.composeuilib.features.misc.SearchBarView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    pokemonListUiState: State<HomeScreenViewModel.PokemonListUiState>,
    loadingIndicatorState: State<Boolean>,
    isDarkModeState: State<Boolean>,
    onInit: () -> Unit,
    onDarkModeChecked: (isDarkMode: Boolean) -> Unit,
    onMenuClicked: () -> Unit,
    onLoadMorePokemon: () -> Unit,
    onSearchClicked: (searchQuery: String) -> Unit,
    onItemClicked: (pokemon: Pokemon) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lazyListState = rememberLazyListState()

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index >= lazyListState.layoutInfo.totalItemsCount - 5
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadMorePokemon()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onInit()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    onDarkModeChecked(isDarkModeState.value)

    Scaffold(
        modifier = modifier
            .wrapContentSize()
            .safeDrawingPadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.battle_base_home)) },
                actions = {
                    IconButton(
                        onClick = {
                            onMenuClicked()
                        }
                    ) {
                        Icon(Icons.Rounded.Menu, stringResource(R.string.battle_base_menu))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBarView { searchQuery ->
                    onSearchClicked(searchQuery)
                }

                LazyColumn(state = lazyListState) {
                    items(
                        count = pokemonListUiState.value.pokemonList.size,
                        key = { index -> pokemonListUiState.value.pokemonList[index].id }
                    ) { index ->
                        CardItemView(
                            itemBuilder = Item.Builder()
                                .title(pokemonListUiState.value.pokemonList[index].name)
                                .imageUrl(pokemonListUiState.value.pokemonList[index].imageUrl)
                        ) {
                            onItemClicked(pokemonListUiState.value.pokemonList[index])
                        }
                    }
                }
                LoadingIndicatorView(isLoading = loadingIndicatorState.value)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        snackbarHostState = remember { SnackbarHostState() },
        pokemonListUiState = remember { mutableStateOf(HomeScreenViewModel.PokemonListUiState()) },
        loadingIndicatorState = remember { mutableStateOf(false) },
        isDarkModeState = remember { mutableStateOf(false) },
        onInit = {},
        onMenuClicked = {},
        onItemClicked = {},
        onLoadMorePokemon = {},
        onSearchClicked = {},
        onDarkModeChecked = {}
    )
}