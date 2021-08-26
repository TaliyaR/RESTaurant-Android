package com.example.restaurant.repositories

import com.example.restaurant.data.network.ApiErrorResponse
import com.example.restaurant.data.network.ApiResult
import com.example.restaurant.data.network.ServerError
import com.example.restaurant.extesions.tryOrNull
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    protected suspend fun <T : Any> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> T
    ): ApiResult<T> {
        return withContext(dispatcher) {
            try {
                ApiResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ApiResult.Error(throwable)
                    is HttpException -> {
                        val serverError = convertErrorBody(throwable)
                        ApiResult.Error(serverError)
                    }
                    else -> {
                        ApiResult.Error(throwable)
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ServerError {
        try {
            throwable.response()?.errorBody()?.source()?.let {
                val code = throwable.code()
                val resString = it.readUtf8()
                val response = tryOrNull {
                    Gson().fromJson(resString, ApiErrorResponse::class.java)
                }
                return ServerError(code, response)
            }

            return ServerError(throwable.code(), null)
        } catch (exception: Exception) {
            return ServerError(throwable.code(), null)
        }
    }
}