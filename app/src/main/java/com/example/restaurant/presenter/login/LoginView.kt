package com.example.restaurant.presenter.login

import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface LoginView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun mainCookScreenOpen()
}