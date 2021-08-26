package com.example.restaurant.data.network

sealed class ApiResult<out R : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val throwable: Throwable): ApiResult<Nothing>()
}