package com.example.restaurant.data.network

import com.example.restaurant.entities.*
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/api/signIn")
    suspend fun signIn(@Body signInRequest: SignInRequest): SignInResponse

    @POST("/api/table/reserve")
    suspend fun reserveTable(@Body id: TableId): ResponseBody

    @GET("/api/order/table")
    suspend fun getOrderByTable(@Query("tableId") tableId: String): Order

    @GET("/api/employee/current")
    suspend fun getCurrentEmployee(): Employee
}