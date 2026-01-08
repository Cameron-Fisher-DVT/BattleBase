package za.co.dvt.composeuilib.features.information

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions

@Composable
fun LineItemView(
    modifier: Modifier = Modifier,
    label: String,
    value: String = ""
) {
    val dimensions = LocalDimensions.current
    Surface(
        modifier = modifier.fillMaxWidth()
            .padding(bottom = dimensions.dp8),
        shape = RoundedCornerShape(dimensions.dp8),
        shadowElevation = dimensions.dp4,
        tonalElevation = dimensions.dp8
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensions.dp16),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            if (value.isNotBlank()) {
                Text(
                    text = value,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun LineItemViewPreview() {
    LineItemView(label = "Label", value = "Value")
}