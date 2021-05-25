package com.example.restaurant.presenter.current

import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CurrentView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTableInfo(number: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTimeInfo(str: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWaiterInfo(str: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setOrderList(list: List<Position>)
}