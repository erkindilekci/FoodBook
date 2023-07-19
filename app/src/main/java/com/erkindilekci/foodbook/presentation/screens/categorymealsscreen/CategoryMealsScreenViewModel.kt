package com.erkindilekci.foodbook.presentation.screens.categorymealsscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.foodbook.data.model.MostPopularMeal
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMealsScreenViewModel @Inject constructor(
    private val repository: FoodRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _meals = MutableStateFlow<List<MostPopularMeal>>(emptyList())
    val meals: StateFlow<List<MostPopularMeal>> = _meals.asStateFlow()

    init {
        val category = savedStateHandle.get<String>("category")
        category?.let {
            getMealsByCategory(it)
        }
    }

    private fun getMealsByCategory(category: String) = viewModelScope.launch {
        try {
            val response = repository.getPopularMeals(category)
            if (response.isSuccessful) {
                response.body()?.let {
                    _meals.value = it.meals
                }
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            println("Error: ${e.localizedMessage}")
        }
    }
}
