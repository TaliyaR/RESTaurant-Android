package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Menu (
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("restaurant")
    val restaurant: Restaurant
)