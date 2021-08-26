package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("account")
    val account: Account
)
