package com.example.hstest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hstest.data.db.dao.DishDao
import com.example.hstest.data.db.entity.DishEntity

@Database(entities = [DishEntity::class], version = 1, exportSchema = false)

abstract class AppDb: RoomDatabase() {
abstract fun dishDao(): DishDao
}