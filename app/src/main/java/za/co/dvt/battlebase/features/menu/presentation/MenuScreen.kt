package za.co.dvt.battlebase.features.menu.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import za.co.dvt.battlebase.R
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions
import za.co.dvt.composeuilib.features.misc.ToggleView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    isDarkModeState: MutableState<Boolean>,
    onDarkModeToggled: (isDarkMode: Boolean) -> Unit,
    onNavigateUpClicked: () -> Unit
) {
    val dimensions = LocalDimensions.current

    Scaffold(
        modifier = modifier.wrapContentSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.battle_base_menu)) },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateUpClicked() }
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, stringResource(R.string.battle_base_menu_arrow_back))
                    }
                },
                actions = {

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
            Column(modifier = modifier.padding(dimensions.dp16)) {
                Text(
                    modifier = modifier.padding(start = dimensions.dp16),
                    text = stringResource(R.string.battle_base_menu_settings),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                ToggleView(
                    title = stringResource(R.string.battle_base_menu_dark_mode),
                    isDarkMode = isDarkModeState.value,
                    onToggled = { isDarkMode ->
                        isDarkModeState.value = isDarkMode
                        onDarkModeToggled(isDarkMode)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen(
        isDarkModeState = remember { mutableStateOf(false) },
        onDarkModeToggled = {},
        onNavigateUpClicked = {}
    )
}
