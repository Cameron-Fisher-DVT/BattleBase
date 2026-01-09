package za.co.dvt.composeuilib.common.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val dp1: Dp = 1.dp,
    val dp2: Dp = 2.dp,
    val dp3: Dp = 3.dp,
    val dp4: Dp = 4.dp,
    val dp5: Dp = 5.dp,
    val dp6: Dp = 6.dp,
    val dp8: Dp = 8.dp,
    val dp10: Dp = 10.dp,
    val dp12: Dp = 12.dp,
    val dp14: Dp = 14.dp,
    val dp16: Dp = 16.dp,
    val dp20: Dp = 20.dp,
    val dp24: Dp = 24.dp,
    val dp28: Dp = 28.dp,
    val dp32: Dp = 32.dp,
    val dp40: Dp = 40.dp,
    val dp48: Dp = 48.dp,
    val dp56: Dp = 56.dp,
    val dp64: Dp = 64.dp,
    val dp80: Dp = 80.dp,
    val dp96: Dp = 96.dp,
    val dp100: Dp = 100.dp,
    val dp120: Dp = 120.dp,
    val dp150: Dp = 150.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }