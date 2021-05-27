package com.example.restaurant.presenter.freeDish

import com.example.restaurant.entities.StatusType
import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.EmployeeRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FreeDishPresenter @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : BasePresenter<FreeDishView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getList()
    }

    fun updateList() {
        getList()
        viewState.setProgressBar(false)
        viewState.stopRefresh()
    }

    private fun getList() {
        viewState.setProgressBar(true)
        launch {
            handleResult(employeeRepository.getPositionByStatus(StatusType.CREATED), {
                viewState.setList(it.data)
                viewState.setProgressBar(false)
            }, {
                handleError(it)
            })
        }
    }
}