package com.example.restaurant.repositories

import com.example.restaurant.data.network.ApiResult
import com.example.restaurant.data.network.ApiService
import com.example.restaurant.data.storage.DataPref
import com.example.restaurant.entities.SignInRequest
import com.example.restaurant.entities.SignInResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataPref: DataPref
) : BaseRepository() {

    suspend fun signIn(email: String, password: String): ApiResult<SignInResponse> {
        return safeApiCall { apiService.signIn(SignInRequest(email, password)) }
    }

    fun isSignedIn(): Boolean {
        return dataPref.getAuthCookie().isNullOrEmpty()
    }

    fun logOut(){
        dataPref.deleteAuthCookie()
    }

}