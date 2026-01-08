package za.co.dvt.battlebase.common.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColorScheme(
    tertiary = ThemeColors.Dark.tertiary,
    onTertiary = ThemeColors.Dark.onTertiary,
    tertiaryContainer = ThemeColors.Dark.tertiaryContainer,
    onTertiaryContainer = ThemeColors.Dark.onTertiaryContainer,

    secondary = ThemeColors.Dark.secondary,
    onSecondary = ThemeColors.Dark.onSecondary,
    secondaryContainer = ThemeColors.Dark.secondaryContainer,
    onSecondaryContainer = ThemeColors.Dark.onSecondaryContainer,

    primary = ThemeColors.Dark.primary,
    onPrimary = ThemeColors.Dark.onPrimary,
    primaryContainer = ThemeColors.Dark.primaryContainer,
    onPrimaryContainer = ThemeColors.Dark.onPrimaryContainer,

    surface = ThemeColors.Dark.surface,
    onSurface = ThemeColors.Dark.text,
    background = ThemeColors.Dark.background
)

private val lightColorPalette = lightColorScheme(
    tertiary = ThemeColors.Light.tertiary,
    onTertiary = ThemeColors.Light.onTertiary,
    tertiaryContainer = ThemeColors.Light.tertiaryContainer,
    onTertiaryContainer = ThemeColors.Light.onTertiaryContainer,

    secondary = ThemeColors.Light.secondary,
    onSecondary = ThemeColors.Light.onSecondary,
    secondaryContainer = ThemeColors.Light.secondaryContainer,
    onSecondaryContainer = ThemeColors.Light.onSecondaryContainer,

    primary = ThemeColors.Light.primary,
    onPrimary = ThemeColors.Light.onPrimary,
    primaryContainer = ThemeColors.Light.primaryContainer,
    onPrimaryContainer = ThemeColors.Light.onPrimaryContainer,

    surface = ThemeColors.Light.surface,
    background = ThemeColors.Light.background
)

@Composable
fun BattleBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) darkColorPalette else lightColorPalette,
        typography = Typography,
        content = content
    )
}