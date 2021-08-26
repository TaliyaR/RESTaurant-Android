package com.example.restaurant.presenter.profile

import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


interface ProfileView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openNavigationActivity()
}