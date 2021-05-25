package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

data class Dish (
    @SerializedName("id")
    val id: Long,
    @SerializedName("menu")
    val menu: Menu,
    @SerializedName("dishType")
    val dishType: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("cost")
    val cost: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("composition")
    val composition: String,
    @SerializedName("weight")
    val weight: Long
)