package com.erkindilekci.foodbook.util.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object FavoritesScreen : Screen("favorites_screen")
    object CategoryScreen : Screen("category_screen")

    object SearchScreen : Screen("search_screen")
    object CategoryMealsScreen : Screen("category_meals_screen/{category}") {
        fun passCategory(category: String): String {
            return "category_meals_screen/$category"
        }
    }
    object DetailsScreen : Screen("details_screen/{id}") {
        fun passId(id: String): String {
            return "details_screen/$id"
        }
    }
}
