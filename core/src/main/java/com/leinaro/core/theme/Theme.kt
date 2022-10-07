package com.leinaro.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColors = darkColors(
  primary = Purple80,
  primaryVariant = PurpleGrey80,
  secondary = Pink80
)

private val LightColors = lightColors(
  primary = CgRed,
  primaryVariant = DarkCandyAppleRed,
  secondary = Bittersweet
)

@Composable
fun MarvelTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colors = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      if (darkTheme) DarkColors else LightColors
    }
    darkTheme -> DarkColors
    else -> LightColors
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      (view.context as Activity).window.statusBarColor = colors.primary.toArgb()
      ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    content = content
  )
}