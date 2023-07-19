package com.erkindilekci.foodbook.presentation.screens.searchscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.foodbook.data.model.Meal
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    var searchQuery = mutableStateOf("")
        private set

    private val _searchedMeals = MutableStateFlow<List<Meal>>(emptyList())
    val searchedMeals: StateFlow<List<Meal>> = _searchedMeals.asStateFlow()

    fun getSearchedMeal(search: String) = viewModelScope.launch {
        try {
            val response = repository.getMealsBySearch(search)
            if (response.isSuccessful) {
                response.body()?.let {
                    _searchedMeals.value = it.meals
                }
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            println("Error: ${e.localizedMessage}")
        }
    }

    fun updateSearchQuery(newQuery: String) {
        searchQuery.value = newQuery
    }
}
