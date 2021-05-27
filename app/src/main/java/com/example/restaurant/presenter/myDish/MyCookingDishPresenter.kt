package com.example.restaurant.presenter.myDish

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.EmployeeRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MyCookingDishPresenter @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : BasePresenter<MyCookingDishView>() {

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
            handleResult(employeeRepository.getCookingPosition(), {
                viewState.setList(it.data)
                viewState.setProgressBar(false)
            }, {
                handleError(it)
            })
        }
    }
}