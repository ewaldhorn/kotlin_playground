package za.co.nofuss.posing.theme

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.lightColorPalette

val green = Color(0xFF1AB940)
private val themeColors = lightColorPalette(
    primary = green,
    surface = Color.DarkGray,
    onSurface = green
)

@Composable
fun MyAppTheme(children: @Composable() () -> Unit) {
    MaterialTheme(colors = themeColors) {
        children()
    }
}