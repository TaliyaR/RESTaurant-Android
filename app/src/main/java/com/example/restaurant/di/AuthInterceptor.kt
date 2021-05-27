package com.example.restaurant.di

import com.example.restaurant.data.storage.DataPref
import com.example.restaurant.repositories.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val dataPref: DataPref
    ) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (dataPref.getAuthToken() != null) {
            val request =
                chain.request().newBuilder().addHeader("Authorization", dataPref.getAuthToken())
                    .build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request())
    }
}