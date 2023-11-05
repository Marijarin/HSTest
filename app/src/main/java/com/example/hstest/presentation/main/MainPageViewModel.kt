package com.example.hstest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hstest.domain.model.Dish
import com.example.hstest.domain.repository.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val repository: DishRepository,
) : ViewModel() {

    init {
        loadDishes()
    }

    val data: Flow<List<Dish>> = repository.data
        .map { list -> list.map { dish -> dish.toDish() } }
        .flowOn(Dispatchers.Default)

    private fun loadDishes() {
        viewModelScope.launch {
            try {
                repository.getAll()
            } catch (e: Exception) {
                throw e.fillInStackTrace()
            }
        }
    }
}