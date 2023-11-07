package com.example.hstest.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hstest.domain.model.Dish
import com.example.hstest.domain.repository.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
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

    private val _tags: MutableStateFlow<List<String>> = MutableStateFlow(emptyList());

    private val tags: StateFlow<List<String>> = _tags.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val data: Flow<List<Dish>> = tags.flatMapLatest { tags ->
        if (tags.isEmpty()) {
            repository.data
                .map { list -> list.map { dish -> dish.toDish() } }
        } else {
            repository.data
                .map { list ->
                    list.map { dish -> dish.toDish() }.filter { tags.contains(it.strCategory) }
                }
        }
    }
        .flowOn(Dispatchers.Default)

    private fun loadDishes() {
        viewModelScope.launch {
            try {
                repository.getAll()
            } catch (e: Exception) {
                Log.w("Load error", e.message.toString())
            }
        }
    }

    fun addTag(tag: String) {
        val mList = _tags.value.toMutableList()
        mList.add(tag)
        _tags.value = mList
    }

    fun removeTag(tag: String) {
        val mList = _tags.value.toMutableList()
        mList.remove(tag)
        _tags.value = mList
    }
}