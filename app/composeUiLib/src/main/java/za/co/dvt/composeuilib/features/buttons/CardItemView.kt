package za.co.dvt.composeuilib.features.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import za.co.dvt.composeuilib.R
import za.co.dvt.composeuilib.common.domain.model.Item
import za.co.dvt.composeuilib.common.presentation.ui.theme.LocalDimensions

@Composable
fun CardItemView(
    modifier: Modifier = Modifier,
    itemBuilder: Item.Builder,
    onClick: (item: Item) -> Unit
) {
    val dimensions = LocalDimensions.current
    val item = itemBuilder.build()
    Card(
        modifier = modifier
            .padding(dimensions.dp16)
            .wrapContentSize()
            .clickable { onClick(item) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensions.dp10,
            pressedElevation = dimensions.dp16
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensions.dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "itemImage",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .width(dimensions.dp100)
                    .height(dimensions.dp120)
            )
            Column(
                modifier = Modifier.padding(start = dimensions.dp16, end = dimensions.dp16)
            ) {
                Text(
                    text = item.title,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.subTitle,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomCardItemView() {
    CardItemView(
        itemBuilder = Item.Builder()
            .title("Title")
            .subTitle("SubTitle")
    ) {
    }
}