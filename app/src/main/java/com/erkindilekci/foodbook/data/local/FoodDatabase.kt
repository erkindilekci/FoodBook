package com.erkindilekci.foodbook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.erkindilekci.foodbook.data.model.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
}
