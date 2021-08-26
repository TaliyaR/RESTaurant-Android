package com.example.restaurant.presenter.main

import com.example.restaurant.Screens
import com.example.restaurant.presenter.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.CurrentScreen)
    }

    fun openOrderScreen() {
        router.newRootScreen(Screens.CurrentScreen)
    }

    fun openHistoryScreen() {
        router.newRootScreen(Screens.HistoryScreen)
    }

    fun openProfileScreen() {
        router.newRootScreen(Screens.ProfileScreen)
    }
}