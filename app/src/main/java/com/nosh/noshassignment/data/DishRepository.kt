package com.nosh.noshassignment.data

class DishRepository(private val dishSource: DishDataSource = RemoteDishDataSource()) {

    suspend fun fetchDishes(): List<Dish> {
        return dishSource.fetchRecommendedDishes()
    }
}