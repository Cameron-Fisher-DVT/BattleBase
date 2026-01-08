package za.co.dvt.battlebase.features.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import za.co.dvt.battlebase.common.presentation.ui.theme.LocalDimensions
import za.co.dvt.battlebase.features.home.domain.model.Ability
import za.co.dvt.battlebase.features.home.domain.model.Pokemon
import za.co.dvt.battlebase.features.home.domain.model.Stat
import za.co.dvt.composeuilib.R
import za.co.dvt.composeuilib.features.information.LineItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeInformationScreen(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    snackbarHostState: SnackbarHostState,
    onFavoriteClicked: (pokemon: Pokemon) -> Unit,
    onNavigateUpClicked: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val dimensions = LocalDimensions.current
    val scrollState = rememberScrollState()

    BottomSheetScaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(pokemon.name) },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateUpClicked() }
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, stringResource(za.co.dvt.battlebase.R.string.battle_base_menu_arrow_back))
                    }
                },
                actions = {
                    IconButton(
                        onClick = { onNavigateUpClicked() }
                    ) {
                        Icon(
                            modifier = modifier.clickable {
                                onFavoriteClicked(pokemon.copy(isFavourite = !pokemon.isFavourite))
                            },
                            imageVector = if (pokemon.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = stringResource(za.co.dvt.battlebase.R.string.battle_base_menu_arrow_back),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        },
        sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(dimensions.dp16)
            ) {
                Text(
                    text = "Information",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

                if (pokemon.abilityList.isNotEmpty()) {

                    Text(
                        modifier = modifier.padding(dimensions.dp16),
                        text = "Ability",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    pokemon.abilityList.forEach {
                        LineItemView(label = it.name, value = "")
                    }
                }
                if (pokemon.statsList.isNotEmpty()) {
                    Text(
                        modifier = modifier.padding(dimensions.dp16),
                        text = "Stats",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    pokemon.statsList.forEach {
                        LineItemView(label = it.name, value = it.score.toString())
                    }
                }
            }
        },
        sheetPeekHeight = dimensions.dp100,
        sheetShadowElevation = dimensions.dp10,
        sheetSwipeEnabled = true,
        scaffoldState = scaffoldState
    ) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = "itemImage",
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .width(dimensions.dp120)
                        .height(dimensions.dp150)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeInformationScreenPreview() {
    HomeInformationScreen(
        snackbarHostState = remember { SnackbarHostState() },
        onNavigateUpClicked = {},
        pokemon = Pokemon(
            pokemonId = "ID",
            name = "Pika",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/44.png",
            abilityList = listOf(
                Ability("ID", "Ability A"),
                Ability("ID", "Ability B"),
                Ability("ID", "Ability C"),
                Ability("ID", "Ability D"),
                Ability("ID", "Ability E"),
                Ability("ID", "Ability F"),
                Ability("ID", "Ability G")

            ),
            statsList = listOf(
                Stat(10, "Stat A"),
                Stat(10, "Stat B"),
                Stat(10, "Stat C"),
                Stat(10, "Stat D"),
                Stat(10, "Stat E"),
                Stat(10, "Stat F"),
                Stat(10, "Stat G"),
                Stat(10, "Stat H")

            ),
            isFavourite = false
        ),
        onFavoriteClicked = {}
    )
}