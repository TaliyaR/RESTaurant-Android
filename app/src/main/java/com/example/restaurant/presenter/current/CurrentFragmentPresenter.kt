package com.example.restaurant.presenter.current

import android.annotation.SuppressLint
import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.ClientRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@InjectViewState
class CurrentFragmentPresenter @Inject constructor(
    private val clientRepository: ClientRepository
) : BasePresenter<CurrentView>() {

    fun getOrderByTable() {
        viewState.setProgressBar(true)
        launch {
            val tableId = clientRepository.getTableId()
            handleResult(clientRepository.getOrderByTable(tableId!!), {
                viewState.setProgressBar(false)
                val order = it.data
                viewState.setTableInfo(order.table.number.toString())
                viewState.setTimeInfo(timeFormat(order.createTime))
                viewState.setWaiterInfo(" ${order.employee?.lastName ?: "###"} ${order.employee?.firstName ?: ""}")
                viewState.setOrderList(order.positions)
                viewState.setTotal(getCost(order.positions))
            }, {
                viewState.thanksDialog()
//                handleError(it)
            })
        }
    }

    fun updateList() {
        getOrderByTable()
        viewState.setProgressBar(false)
        viewState.stopRefresh()
    }

    fun cleanId() {
        clientRepository.setTableId(null)
    }

    private fun getCost(position: List<Position>): Int {
        var total = 0
        position.forEach {
            total += it.dish.cost.toInt()
        }
        return total
    }

    @SuppressLint("NewApi")
    private fun timeFormat(string: String): String =
        DateTimeFormatter.ofPattern("HH:mm").format(
            OffsetDateTime.parse(string).atZoneSameInstant(
                ZoneId.systemDefault()
            )
        )
}