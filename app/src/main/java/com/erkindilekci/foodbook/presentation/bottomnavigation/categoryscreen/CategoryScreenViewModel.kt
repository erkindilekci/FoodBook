package com.erkindilekci.foodbook.presentation.bottomnavigation.categoryscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.foodbook.data.model.Category
import com.erkindilekci.foodbook.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>?>(null)
    val categories: StateFlow<List<Category>?> = _categories.asStateFlow()

    init {
        getCategories()
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
