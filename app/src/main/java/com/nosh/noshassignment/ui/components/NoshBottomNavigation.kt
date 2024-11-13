package com.nosh.noshassignment.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nosh.noshassignment.ui.theme.NoshIcons
import com.nosh.noshassignment.ui.theme.NoshTheme

@Composable
fun NoshBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(
                imageVector = ImageVector.vectorResource(NoshIcons.Cook),
                contentDescription = null
            )
        }, label = {
            Text(text = "Cook")
        })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
        }, label = {
            Text(text = "Favorites")
        })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                imageVector = ImageVector.vectorResource(NoshIcons.ManualCook),
                contentDescription = null
            )
        }, label = {
            Text(text = "Manual")
        })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                imageVector = ImageVector.vectorResource(NoshIcons.Device),
                contentDescription = null
            )
        }, label = {
            Text(text = "Device")
        })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
        }, label = {
            Text(text = "Settings")
        })
    }
}

@Composable
fun NoshNavigationRail(modifier: Modifier = Modifier) {
    val start = WindowInsets.safeContent
        .asPaddingValues()
        .calculateStartPadding(
            LocalLayoutDirection.current
        )
    NavigationRail(
        modifier = modifier.padding(start = start, end = 8.dp),
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(selected = true, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(NoshIcons.Cook),
                    contentDescription = null
                )
            }, label = {
                Text(text = "Cook")
            })
            NavigationRailItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
            }, label = {
                Text(text = "Favorites")
            })
            NavigationRailItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(NoshIcons.ManualCook),
                    contentDescription = null
                )
            }, label = {
                Text(text = "Manual")
            })
            NavigationRailItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(NoshIcons.Device),
                    contentDescription = null
                )
            }, label = {
                Text(text = "Device")
            })
            NavigationRailItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }, label = {
                Text(text = "Settings")
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    NoshTheme { NoshBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true
)
@Composable
fun NavigationRailPreview() {
    NoshTheme { NoshNavigationRail() }
}