package com.example.restaurant.data.network

class ServerError(
    val errorCode: Int,
    val errorResponse: ApiErrorResponse?
) : RuntimeException()