package com.example.restaurant.data.network

import com.example.restaurant.entities.Order
import com.example.restaurant.entities.SignInRequest
import com.example.restaurant.entities.TableId
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/api/signIn")
    suspend fun signIn(signInRequest: SignInRequest)

    @POST("/api/table/reserve")
    suspend fun reserveTable(@Body id: TableId): ResponseBody

    @GET("/api/order/table")
    suspend fun getOrderByTable(@Query("tableId") tableId: String): Order
}