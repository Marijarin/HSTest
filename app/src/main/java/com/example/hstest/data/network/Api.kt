package com.example.hstest.data.network

import com.example.hstest.data.dto.DishDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{
@GET("search.php?f=p")
suspend fun getDishes(): Response<Map<String, List<DishDTO>>>
}