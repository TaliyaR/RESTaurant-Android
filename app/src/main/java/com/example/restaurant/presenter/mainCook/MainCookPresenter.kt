package com.example.restaurant.presenter.mainCook

import com.example.restaurant.Screens
import com.example.restaurant.presenter.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainCookPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainCookView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.FreeDishScreen)
    }

    fun openFreeDishScreen() {
        router.newRootScreen(Screens.FreeDishScreen)
    }

    fun openMyDishScreen() {
        router.newRootScreen(Screens.MyDishScreen)
    }

    fun openProfileScreen() {
        router.newRootScreen(Screens.EmployeeProfileScreen)
    }
}