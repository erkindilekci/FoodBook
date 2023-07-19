package com.erkindilekci.foodbook.presentation.bottomnavigation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.foodbook.data.model.Category
import com.erkindilekci.foodbook.data.model.Meal
import com.erkindilekci.foodbook.data.model.MostPopularMeal
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _randomMeal = MutableStateFlow<Meal?>(null)
    val randomMeal: StateFlow<Meal?> = _randomMeal.asStateFlow()

    private val _mostPopularMeals = MutableStateFlow<List<MostPopularMeal>>(emptyList())
    val mostPopularMeals: StateFlow<List<MostPopularMeal>> = _mostPopularMeals.asStateFlow()

    private val _categories = MutableStateFlow<List<Category>?>(null)
    val categories: StateFlow<List<Category>?> = _categories.asStateFlow()

    init {
        getRandomMeals()
        getPopularMeals()
        getCategories()
    }

    private fun getRandomMeals() = viewModelScope.launch {
        try {
            val response = repository.getRandomMeals()
            if (response.isSuccessful) {
                response.body()?.let {
                    val randomMeal = it.meals.first()
                    _randomMeal.value = randomMeal
                }
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            println("Error : ${e.message}")
        }
    }

    private fun getPopularMeals() = viewModelScope.launch {
        try {
            val response = repository.getPopularMeals("beef")
            if (response.isSuccessful) {
                response.body()?.let {
                    _mostPopularMeals.value = it.meals
                }
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            println("Error : ${e.message}")
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _categories.value = it.categories
                    }
                } else {
                    println(response.message())
                }
            } catch (e: Exception) {
                println("Error : ${e.message}")
            }
        }
    }
}
