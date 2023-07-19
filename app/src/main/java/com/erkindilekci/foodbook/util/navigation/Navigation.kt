package com.erkindilekci.foodbook.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erkindilekci.foodbook.presentation.bottomnavigation.categoryscreen.CategoryScreen
import com.erkindilekci.foodbook.presentation.bottomnavigation.favoritesscreen.FavoritesScreen
import com.erkindilekci.foodbook.presentation.bottomnavigation.homescreen.HomeScreen
import com.erkindilekci.foodbook.presentation.screens.categorymealsscreen.CategoryMealsScreen
import com.erkindilekci.foodbook.presentation.screens.detailsscreen.DetailsScreen
import com.erkindilekci.foodbook.presentation.screens.searchscreen.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.FavoritesScreen.route) {
            FavoritesScreen(navController = navController)
        }
        composable(Screen.CategoryScreen.route) {
            CategoryScreen(navController = navController)
        }

        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.CategoryMealsScreen.route) {
            CategoryMealsScreen(navController = navController)
        }
        composable(Screen.DetailsScreen.route) {
            DetailsScreen(navController = navController)
        }
    }
}
