package com.example.restaurant.presenter.splash

import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SplashView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openNavigationActivity()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openMainActivity()
}