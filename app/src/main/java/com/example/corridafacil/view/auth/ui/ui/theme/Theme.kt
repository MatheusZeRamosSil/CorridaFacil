package com.example.corridafacil.view.auth.ui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = Purple700,
    secondary = Amber
)

private val AppPalletColors = Colors(
    White,
    Black,
    Blue,
    Purple700,
    BlueOcean,
    Amber,
    YellowGradientCircle,
    AmberGradientCircle,
    DarkYellowGradientCircle,
    DarkYellowGradientCircle200,
    Blue,
    Purple700,
    false
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = Purple700,
    secondary = Amber,
    surface = Blue,
    onSurface = BlueOcean,


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CorridaFacilTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = AppPalletColors


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

}