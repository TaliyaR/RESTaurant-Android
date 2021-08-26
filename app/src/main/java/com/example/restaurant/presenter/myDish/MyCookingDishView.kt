package com.example.restaurant.presenter.myDish

import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


interface MyCookingDishView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProgressBar(boolean: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setList(list: List<Position>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmptyState(boolean: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun confirmationDialog(position: Position)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun stopRefresh()
}