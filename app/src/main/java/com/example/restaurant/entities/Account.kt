package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Account (
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("role")
    val role: String
)