package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("id")
    val id: Long,
    @SerializedName("dish")
    val dish: Dish,
    @SerializedName("cook")
    val cook: Employee,
    @SerializedName("status")
    val status: String
)