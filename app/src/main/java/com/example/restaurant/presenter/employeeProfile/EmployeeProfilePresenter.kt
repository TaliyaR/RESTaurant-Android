package com.example.restaurant.presenter.employeeProfile

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.AuthRepository
import com.example.restaurant.repositories.EmployeeRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class EmployeeProfilePresenter @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val authRepository: AuthRepository
) : BasePresenter<EmployeeProfileView>() {

    override fun onFirstViewAttach() {
        viewState.setProgressBar(true)
        launch {
            handleResult(employeeRepository.getCurrentEmployee(), {
                viewState.setProgressBar(false)
                viewState.setEmployeeInfo(it.data)
            }, {
                handleError(it)
            })
        }
    }

    fun logout() {
        authRepository.logOut()
        viewState.openNavigationActivity()
    }
}