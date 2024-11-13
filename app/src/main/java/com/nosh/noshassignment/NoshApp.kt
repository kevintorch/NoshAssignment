package com.nosh.noshassignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.nosh.noshassignment.ui.components.NoshBottomNavigation
import com.nosh.noshassignment.ui.components.NoshNavigationRail
import com.nosh.noshassignment.ui.screens.MainScreen
import com.nosh.noshassignment.ui.screens.NoshDestinations
import com.nosh.noshassignment.ui.theme.NoshTheme

@Composable
fun NoshApp(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> NoshAppPortrait()
        else -> NoshAppLandscape()
    }
}

@Composable
fun AdaptiveMainScreen(modifier: Modifier = Modifier) {
    var selected by rememberSaveable { mutableStateOf(NoshDestinations.COOK) }
    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            NoshDestinations.entries.forEach {
                item(
                    selected = selected == it,
                    onClick = { selected = it },
                    icon = { Icon(it.icon, contentDescription = null) },
                    label = { Text(it.title) },
                )
            }
        }
    ) {
        MainScreen()
    }
}

@Composable
fun NoshAppPortrait() {
    NoshTheme {
        Scaffold(bottomBar = {
            NoshBottomNavigation(modifier = Modifier.safeContentPadding())
        }) { padding ->
            MainScreen(modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun NoshAppLandscape() {
    NoshTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Row {
                NoshNavigationRail()
                Box(Modifier.shadow(2.dp)) {
                    MainScreen()
                }
            }
        }
    }
}