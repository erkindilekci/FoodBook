package com.erkindilekci.foodbook.presentation.bottomnavigation.util

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.erkindilekci.foodbook.R
import com.erkindilekci.foodbook.util.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier.height(80.dp),
        contentColor = MaterialTheme.colorScheme.onBackground,

        ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(R.string.home_icon)
                )
            },
            label = { Text(text = "Home") },
            selected = currentDestination?.hierarchy?.any { destination ->
                destination.route == Screen.HomeScreen.route
            } == true,
            onClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite_icon)
                )
            },
            label = { Text(text = "Favorites") },
            selected = currentDestination?.hierarchy?.any { destination ->
                destination.route == Screen.FavoritesScreen.route
            } == true,
            onClick = {
                navController.navigate(Screen.FavoritesScreen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Category,
                    contentDescription = stringResource(id = R.string.category_icon)
                )
            },
            label = { Text(text = "Categories") },
            selected = currentDestination?.hierarchy?.any { destination ->
                destination.route == Screen.CategoryScreen.route
            } == true,
            onClick = {
                navController.navigate(Screen.CategoryScreen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

    }
}
