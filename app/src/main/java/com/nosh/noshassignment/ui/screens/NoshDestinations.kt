package com.nosh.noshassignment.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NoshDestinations(
    val title: String,
    val icon: ImageVector,
    val contentDescription: String? = null,
) {
    COOK("Cook", Icons.Outlined.Kitchen, "Cook"),
    SETTINGS("Settings", Icons.Outlined.Settings, "Settings")
}