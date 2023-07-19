package com.erkindilekci.foodbook.data.repository

import com.erkindilekci.foodbook.data.local.FoodDao
import com.erkindilekci.foodbook.data.model.CategoryList
import com.erkindilekci.foodbook.data.model.Meal
import com.erkindilekci.foodbook.data.remote.FoodApi
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import retrofit2.Response

class FoodRepositoryImpl(
    private val api: FoodApi,
    private val dao: FoodDao
) : FoodRepository {

    override suspend fun getRandomMeals() = api.getRandomMeals()

    override suspend fun getMealsDetail(
        id: String
    ) = api.getMealsDetail(id)

    override suspend fun getPopularMeals(
        category: String
    ) = api.getPopularMeals(category)

    override suspend fun getCategories(): Response<CategoryList> {
        return api.getCategories()
    }

    override suspend fun getMealsBySearch(
        s: String
    ) = api.getMealsBySearch(s)

    override suspend fun upsertMeal(
        meal: Meal
    ) = dao.upsertMeal(meal)

    override suspend fun deleteMeal(
        meal: Meal
    ) = dao.deleteMeal(meal)

    override fun getAllFavoriteMeals() = dao.getAllFavoriteMeals()
}
