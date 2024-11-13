package com.nosh.noshassignment.data

import com.nosh.noshassignment.service.DishApiService
import com.nosh.noshassignment.service.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DishDataSource {
    suspend fun fetchRecommendedDishes(): List<Dish>
}

class RemoteDishDataSource(
    private val apiClient: DishApiService = RetrofitClient.apiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): DishDataSource {
    override suspend fun fetchRecommendedDishes(): List<Dish> = withContext(ioDispatcher) {
        apiClient.recommendedDishes().body() ?: emptyList()
    }
}