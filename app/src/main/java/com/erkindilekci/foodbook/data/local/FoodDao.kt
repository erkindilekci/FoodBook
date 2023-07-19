package com.erkindilekci.foodbook.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.erkindilekci.foodbook.data.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Upsert
    suspend fun upsertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM meal_table")
    fun getAllFavoriteMeals(): Flow<List<Meal>>
}
