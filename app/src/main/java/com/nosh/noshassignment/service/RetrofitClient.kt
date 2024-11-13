package com.nosh.noshassignment.service

import com.nosh.noshassignment.data.Dish
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object RetrofitClient {
    private const val BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/"
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService by lazy { retrofit.create(DishApiService::class.java) }
}

interface DishApiService {
    @GET("/dev/nosh-assignment")
    suspend fun recommendedDishes(): Response<List<Dish>>
}