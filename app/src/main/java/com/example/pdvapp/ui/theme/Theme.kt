package com.example.pdvapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import android.os.Build
import androidx.compose.material3.ExperimentalMaterial3Api

// Esquema para tema claro com as cores da marca
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF014029),       // Verde escuro
    onPrimary = Color(0xFFF2F2F2),       // Texto claro sobre primary
    secondary = Color(0xFF1DF2F2),       // Ciano
    onSecondary = Color(0xFF0D0D0D),     // Texto escuro sobre secondary
    background = Color(0xFFF2F2F2),      // Fundo claro
    onBackground = Color(0xFF0D0D0D),    // Texto escuro sobre fundo
    surface = Color(0xFFF2F2F2),         // Superfícies claras
    onSurface = Color(0xFF0D0D0D),       // Texto escuro sobre superfícies
    tertiary = Color(0xFF05F2AF)         // Destaque/terciária
)

// Esquema para tema escuro com as cores da marca
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF014029),
    onPrimary = Color(0xFFF2F2F2),
    secondary = Color(0xFF1DF2F2),
    onSecondary = Color(0xFF0D0D0D),
    background = Color(0xFF0D0D0D),      // Fundo escuro
    onBackground = Color(0xFFF2F2F2),    // Texto claro sobre fundo escuro
    surface = Color(0xFF0D0D0D),
    onSurface = Color(0xFFF2F2F2),
    tertiary = Color(0xFF05F2AF)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdvappTheme(
    darkTheme: Boolean = androidx.compose.foundation.isSystemInDarkTheme(),
    // Dynamic color é disponível a partir do Android 12
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}