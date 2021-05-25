package com.example.restaurant.repositories

import com.example.restaurant.data.network.ApiResult
import com.example.restaurant.data.network.ApiService
import com.example.restaurant.data.storage.DataPref
import com.example.restaurant.entities.Order
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataPref: DataPref
) : BaseRepository() {

    private var _currentOrder: Order? = null

    suspend fun reserveTable(tableId: String) = safeApiCall {
        return@safeApiCall apiService.reserveTable(tableId)
    }

    suspend fun getOrderByTable(tableId: String): ApiResult<Order> {
//        if (_currentOrder != null) {
//            return ApiResult.Success(_currentOrder)
//        }

        val result = safeApiCall { apiService.getOrderByTable(tableId) }
        if (result is ApiResult.Success) {
            _currentOrder = result.data
        }
        return result

    }

    fun setTableId(tableId: String) {
        if(tableId.isNotEmpty()) {
            dataPref.setTableId(tableId)
        }
    }

    fun getTableId(): String? {
     return dataPref.getTableId()
    }
}