package za.co.dvt.composeuilib.features.misc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import za.co.dvt.composeuilib.R
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    modifier: Modifier = Modifier,
    onSearchClicked: (searchQuery: String) -> Unit
) {
    val dimensions = LocalDimensions.current
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = {
            onSearchClicked(it)
            active = false
            searchQuery = ""
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(stringResource(R.string.compose_ui_lib_search))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.compose_ui_lib_search))
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = modifier.clickable {
                        if (searchQuery.isNotEmpty()) {
                            searchQuery = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.compose_ui_lib_close)
                )
            }
        },
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(start = dimensions.dp10, end = dimensions.dp10, bottom = dimensions.dp10)
    ) {

    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchBarViewPreview() {
    SearchBarView(
    ) {

    }
}