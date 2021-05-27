package com.example.restaurant.presenter.employeeProfile

import com.example.restaurant.entities.Employee
import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


interface EmployeeProfileView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setEmployeeInfo(employee: Employee)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProgressBar(boolean: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openNavigationActivity()
}