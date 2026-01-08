package za.co.dvt.composeuilib.features.misc

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions

@Composable
fun DividerView(
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    HorizontalDivider(
        modifier
            .fillMaxWidth()
            .width(dimensions.dp1)
            .padding(dimensions.dp5),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true)
@Composable
fun DividerViewPreview() {
    DividerView()
}