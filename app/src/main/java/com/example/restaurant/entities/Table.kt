package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Table(
    @SerializedName("id")
    val id: String,
    @SerializedName("seatCount")
    val seatCount: Long,
    @SerializedName("restaurant")
    val restaurant: Restaurant,
    @SerializedName("status")
    val status: String,
    @SerializedName("number")
    val number: Long
)