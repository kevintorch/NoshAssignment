package com.nosh.noshassignment.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class DishUiState(
    val dishes: List<Dish> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class MainViewModel(private val repository: DishRepository = DishRepository()) : ViewModel() {

    private val _uiState: MutableStateFlow<DishUiState> = MutableStateFlow(DishUiState())
    val uiState: StateFlow<DishUiState> = _uiState.asStateFlow()

    init {
        fetchRecommendedDishes()
    }

    private fun fetchRecommendedDishes() {
        viewModelScope.launch {
            try {
                _uiState.value = DishUiState(isLoading = true)
                val newDishes = repository.fetchDishes()
                _uiState.value = DishUiState(dishes = newDishes)
            } catch (e: Exception) {
                _uiState.value = DishUiState(errorMessage = e.message)
            }
        }
    }

    fun onErrorShown() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}