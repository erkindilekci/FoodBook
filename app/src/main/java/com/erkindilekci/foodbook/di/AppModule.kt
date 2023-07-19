package com.erkindilekci.foodbook.di

import android.content.Context
import androidx.room.Room
import com.erkindilekci.foodbook.data.local.FoodDao
import com.erkindilekci.foodbook.data.local.FoodDatabase
import com.erkindilekci.foodbook.data.remote.FoodApi
import com.erkindilekci.foodbook.data.repository.FoodRepositoryImpl
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import com.erkindilekci.foodbook.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoodApi(): FoodApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodApi::class.java)

    @Provides
    @Singleton
    fun provideFoodDatabase(
        @ApplicationContext context: Context
    ): FoodDatabase = Room.databaseBuilder(
        context,
        FoodDatabase::class.java,
        "food_db"
    ).build()

    @Provides
    @Singleton
    fun provideFoodDao(db: FoodDatabase): FoodDao = db.foodDao()

    @Provides
    @Singleton
    fun provideFoodRepository(
        api: FoodApi,
        dao: FoodDao
    ): FoodRepository = FoodRepositoryImpl(api, dao)
}
