package com.example.restaurant.presenter.current

import android.annotation.SuppressLint
import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.ClientRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@InjectViewState
class CurrentFragmentPresenter @Inject constructor(
    private val clientRepository: ClientRepository
) : BasePresenter<CurrentView>() {


    fun getOrderByTable(tableId: String) {
        launch {
            handleResult(clientRepository.getOrderByTable(tableId), {
                val order = it.data
                viewState.setTableInfo(order.table.number.toString())
                viewState.setTimeInfo(timeFormat(order.createTime))
                viewState.setWaiterInfo("${order.employee.lastName} ${order.employee.firstName}")
                viewState.setOrderList(order.positions)
            }, {
                handleError(it)
            })
        }
    }

    @SuppressLint("NewApi")
    private fun timeFormat(string: String): String =
        DateTimeFormatter.ofPattern("HH:mm").format(OffsetDateTime.parse(string))
}