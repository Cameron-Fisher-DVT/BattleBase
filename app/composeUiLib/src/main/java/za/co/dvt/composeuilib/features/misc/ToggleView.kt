package za.co.dvt.composeuilib.features.misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions

@Composable
fun ToggleView(
    modifier: Modifier = Modifier,
    title: String,
    isDarkMode: Boolean = false,
    onToggled: (value: Boolean) -> Unit
) {
    val dimensions = LocalDimensions.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensions.dp16),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        Switch(
            checked = isDarkMode,
            onCheckedChange = {
                onToggled(it)
            }
        )
    }
}