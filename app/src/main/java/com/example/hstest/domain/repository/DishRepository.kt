package com.example.hstest.domain.repository

import com.example.hstest.data.db.entity.DishEntity
import kotlinx.coroutines.flow.Flow

interface DishRepository {
    val data: Flow<List<DishEntity>>
    suspend fun getAll()

}