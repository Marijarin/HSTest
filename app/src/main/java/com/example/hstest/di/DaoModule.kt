package com.example.hstest.di

import com.example.hstest.data.db.AppDb
import com.example.hstest.data.db.dao.DishDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    fun provideDishDao(db: AppDb): DishDao = db.dishDao()

}