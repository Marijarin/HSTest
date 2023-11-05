package com.example.hstest.data.repository

import com.example.hstest.data.db.dao.DishDao
import com.example.hstest.data.db.entity.DishEntity
import com.example.hstest.data.db.entity.toEntity
import com.example.hstest.data.network.ApiService
import com.example.hstest.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val dishDao: DishDao,
    private val apiService: ApiService,
) : DishRepository {
    override val data: Flow<List<DishEntity>> = dishDao.getAll()


    override suspend fun getAll() {
        try {
            val response = apiService.getDishes()
            if (!response.isSuccessful) {
                throw Error(response.message())
            }
            val ds = response.body() ?: throw Error(response.message())
            val dishes = ds.values.toList()[0]
            dishDao.insert(dishes.toEntity())
        } catch (e: IOException) {
            throw e.fillInStackTrace()
        } catch (e: Exception) {
            throw e.fillInStackTrace()
        }
    }
}
