package com.erkindilekci.foodbook.data.remote

import com.erkindilekci.foodbook.data.model.CategoryList
import com.erkindilekci.foodbook.data.model.MealList
import com.erkindilekci.foodbook.data.model.MostPopularMealList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("random.php")
    suspend fun getRandomMeals(): Response<MealList>

    @GET("lookup.php?")
    suspend fun getMealsDetail(
        @Query("i") id: String
    ): Response<MealList>

    @GET("filter.php?")
    suspend fun getPopularMeals(
        @Query("c") category: String
    ): Response<MostPopularMealList>

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryList>

    @GET("search.php?")
    suspend fun getMealsBySearch(
        @Query("s") s: String
    ): Response<MealList>
}
