package com.erkindilekci.foodbook.presentation.bottomnavigation.favoritesscreen

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
class FavoritesScreenViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _favoriteMeals = MutableStateFlow<List<Meal>>(emptyList())
    val favoriteMeals: StateFlow<List<Meal>> = _favoriteMeals.asStateFlow()

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repository.getAllFavoriteMeals()
            favorites.collect { collectedFavorites ->
                _favoriteMeals.value = collectedFavorites
            }
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeal(meal)
        }
    }
}
