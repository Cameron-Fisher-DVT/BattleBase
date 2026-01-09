package za.co.dvt.composeuilib.features.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions

@Composable
fun LoadingIndicatorView(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val dimensions = LocalDimensions.current
    if (isLoading) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(dimensions.dp100)
                    .background(Color.White, shape = RoundedCornerShape(dimensions.dp8))
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Preview
@Composable
fun LoadingIndicatorViewPreview() {
    LoadingIndicatorView(isLoading = true)
}