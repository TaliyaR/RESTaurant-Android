package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: String
)