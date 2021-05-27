package com.example.restaurant.repositories

import com.example.restaurant.data.network.ApiService
import javax.inject.Inject


class EmployeeRepository @Inject constructor(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getCurrentEmployee() = safeApiCall {
        return@safeApiCall apiService.getCurrentEmployee()
    }
}