package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id")
    val id: String,
    @SerializedName("table")
    val table: Table,
    @SerializedName("restaurant")
    val restaurant: Restaurant,
    @SerializedName("positions")
    val positions: List<Position>,
    @SerializedName("employee")
    val employee: Employee,
    @SerializedName("status")
    val status: String,
    @SerializedName("createTime")
    val createTime: String
)