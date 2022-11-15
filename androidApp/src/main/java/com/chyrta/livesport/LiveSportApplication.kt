package com.chyrta.livesport

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import com.chyrta.livesport.navigation.LiveSportNavGraph
import com.chyrta.livesport.ui.LiveSportTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LiveSportApplication() {
    val systemUiController = rememberSystemUiController()

    val isLightTheme by rememberSaveable { mutableStateOf<Boolean?>(null) }
    val isLightThemeFinal = isLightTheme ?: !isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = isLightThemeFinal,
        )
    }

    LiveSportTheme(
        isLightTheme = isLightThemeFinal,
    ) {
        LiveSportNavGraph()
    }
}
