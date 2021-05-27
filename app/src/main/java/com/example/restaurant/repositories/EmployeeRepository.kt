package com.example.restaurant.repositories

import com.example.restaurant.data.network.ApiService
import com.example.restaurant.entities.StatusType
import javax.inject.Inject


class EmployeeRepository @Inject constructor(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getCurrentEmployee() = safeApiCall {
        return@safeApiCall apiService.getCurrentEmployee()
    }

    suspend fun getPositionByStatus(status: StatusType) = safeApiCall {
        return@safeApiCall apiService.getPositionByStatus(status)
    }

    suspend fun getCookingPosition() = safeApiCall {
        return@safeApiCall apiService.getCookingPosition()
    }
}