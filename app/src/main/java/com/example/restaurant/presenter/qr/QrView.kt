package com.example.restaurant.presenter.qr

import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface QrView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openMainScreen()
}