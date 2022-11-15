package com.chyrta.livesport.ui

import androidx.compose.runtime.Composable
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.foundation.darkColors
import kiwi.orbit.compose.ui.foundation.lightColors

@Composable
fun LiveSportTheme(
    isLightTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    OrbitTheme(
        colors = when (isLightTheme) {
            true -> lightColors()
            false -> darkColors()
        },
        content = content,
    )
}
