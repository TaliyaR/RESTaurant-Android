package com.example.restaurant.presenter.qr

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.ClientRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class QrActivityPresenter @Inject constructor(
    private val clientRepository: ClientRepository
) : BasePresenter<QrView>() {

    fun getTableIdFromQrResult(str: String) {
        val tableId = str.substringAfterLast("tableId=")
        launch {
            handleResult(clientRepository.reserveTable(tableId), {
                viewState.openMainScreen()
            }, {
                viewState.openMainScreen()
                handleError(it)
            })
        }
    }
}