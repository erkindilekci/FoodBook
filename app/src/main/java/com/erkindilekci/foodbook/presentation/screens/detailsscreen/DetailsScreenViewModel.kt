package com.erkindilekci.foodbook.presentation.screens.detailsscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.foodbook.data.model.Meal
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: FoodRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _mealDetails = MutableStateFlow<Meal?>(null)
    val mealDetails: StateFlow<Meal?> = _mealDetails.asStateFlow()

    init {
        val id = savedStateHandle.get<String>("id")
        id?.let {
            getMealDetail(it)
        }
    }

    private fun getMealDetail(id: String) = viewModelScope.launch {
        try {
            val response = repository.getMealsDetail(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    _mealDetails.value = it.meals.first()
                }
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            println("Error: ${e.localizedMessage}")
        }
    }

    fun upsertUpdate(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertMeal(meal)
        }
    }
}
