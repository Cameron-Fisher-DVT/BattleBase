package za.co.dvt.battlebase.common.presentation.ui.theme

import androidx.compose.ui.graphics.Color

val DarkModeTertiary = Color(0xFF000000)
val DarkModeSecondary = Color(0xFFE2062C)
val DarkModePrimary = Color(0xFF005941)
val DarkModeSurface = Color(0xFF757575)

val LightModeTertiary = Color(0xFF248588)
val LightModeSecondary = Color(0xFFFFF200)
val LightModePrimary = Color(0xFF59A297)
val LightModeSurface = Color(0xFFE9F0F4)

sealed class ThemeColors(
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val background: Color,
    val surface: Color,
    val text: Color
) {
    data object Dark : ThemeColors(
        tertiary = DarkModeTertiary,
        onTertiary = Color.White,
        tertiaryContainer = DarkModeTertiary,
        onTertiaryContainer = Color.White,

        secondary = DarkModeSecondary,
        onSecondary = Color.White,
        secondaryContainer = DarkModeSecondary,
        onSecondaryContainer = Color.White,

        primary = DarkModePrimary,
        onPrimary = Color.White,
        primaryContainer = DarkModePrimary,
        onPrimaryContainer = Color.White,

        background = Color.Black,
        surface = DarkModeSurface,
        text = Color.White,
    )

    data object Light : ThemeColors(
        tertiary = LightModeTertiary,
        onTertiary = Color.White,
        tertiaryContainer = LightModeTertiary,
        onTertiaryContainer = Color.White,

        secondary = LightModeSecondary,
        onSecondary = Color.White,
        secondaryContainer = LightModeSecondary,
        onSecondaryContainer = Color.White,

        primary = LightModePrimary,
        onPrimary = Color.White,
        primaryContainer = LightModePrimary,
        onPrimaryContainer = Color.White,

        background = Color.White,
        surface = LightModeSurface,
        text = Color.Black,
    )
}