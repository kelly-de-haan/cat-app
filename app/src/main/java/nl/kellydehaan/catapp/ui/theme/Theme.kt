package nl.kellydehaan.catapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val primaryColor = Color(0xFF7B5EA7)
private val secondaryColor = Color(0xFFE8A87C)

private val LightColors = lightColorScheme(
    primary = primaryColor,
    onPrimary = Color.White,
    secondary = secondaryColor,
    onSecondary = Color.White,
    background = Color(0xFFF9F6FF),
    surface = Color.White,
    onBackground = Color(0xFF1A1A2E),
    onSurface = Color(0xFF1A1A2E),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFBB9EE0),
    onPrimary = Color(0xFF2A1A4A),
    secondary = secondaryColor,
    background = Color(0xFF1A1A2E),
    surface = Color(0xFF252540),
    onBackground = Color(0xFFF0EAF8),
    onSurface = Color(0xFFF0EAF8),
)

@Composable
fun CatBreedsTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
