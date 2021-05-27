package com.example.restaurant.entities

import com.google.gson.annotations.SerializedName

/**
 * Project RESTaurant
 * Package com.example.restaurant.entities
 *
 *
 *
 * Created by Taliya Arsembekova (aka taliyar) 28.05.2021
 * Copyright © 2021 TKO-Inform. All rights reserved.
 */
data class StatusRequest(
    @SerializedName("id")
    val id: Long,
    @SerializedName("status")
    val status: String
)