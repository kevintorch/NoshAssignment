package com.nosh.noshassignment.data


import com.google.gson.annotations.SerializedName

data class Dish(
    @SerializedName("dishId")
    val dishId: String? = null,
    @SerializedName("dishName")
    val dishName: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("isPublished")
    val isPublished: Boolean? = null
)