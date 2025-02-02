/*
 * Copyright (c) 2022 Juby210 & zt
 * Licensed under the Open Software License version 3.0
 */

package com.aliucord.manager.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.aliucord.manager.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ManagerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && isDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !isDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        isDarkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colorScheme.background,
            darkIcons = !isDarkTheme
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

enum class Theme {
    SYSTEM,
    LIGHT,
    DARK;

    @Composable
    fun toDisplayName() = when (this) {
        SYSTEM -> stringResource(R.string.theme_system)
        LIGHT -> stringResource(R.string.theme_light)
        DARK -> stringResource(R.string.theme_dark)
    }
}
