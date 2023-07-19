package com.erkindilekci.foodbook.domain.repository

import com.erkindilekci.foodbook.data.model.CategoryList
import com.erkindilekci.foodbook.data.model.Meal
import com.erkindilekci.foodbook.data.model.MealList
import com.erkindilekci.foodbook.data.model.MostPopularMealList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface FoodRepository {

    suspend fun getRandomMeals(): Response<MealList>
    suspend fun getMealsDetail(id: String): Response<MealList>
    suspend fun getPopularMeals(category: String): Response<MostPopularMealList>
    suspend fun getCategories(): Response<CategoryList>
    suspend fun getMealsBySearch(s: String): Response<MealList>

    suspend fun upsertMeal(meal: Meal)
    suspend fun deleteMeal(meal: Meal)
    fun getAllFavoriteMeals(): Flow<List<Meal>>
}
