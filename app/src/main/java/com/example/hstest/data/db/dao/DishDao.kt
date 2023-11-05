package com.example.hstest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hstest.data.db.entity.DishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {
    @Query("SELECT * FROM DishEntity ORDER BY idMeal DESC")
    fun getAll(): Flow<List<DishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dish: DishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dishes: List<DishEntity>)

    @Query("DELETE FROM DishEntity WHERE idMeal = :id")
    suspend fun removeById(id: Long)

    @Query("DELETE FROM DishEntity")
    suspend fun removeAll()
}