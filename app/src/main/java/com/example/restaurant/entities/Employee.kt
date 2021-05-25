package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Employee (
    @SerializedName("id")
    val id: String,
    @SerializedName("account")
    val account: Account,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("fatherName")
    val fatherName: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("restaurant")
    val restaurant: Restaurant
)