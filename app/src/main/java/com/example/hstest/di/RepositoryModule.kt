package com.example.hstest.di

import com.example.hstest.data.repository.DishRepositoryImpl
import com.example.hstest.domain.repository.DishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsDishRepository(impl: DishRepositoryImpl): DishRepository
}