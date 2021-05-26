package com.example.restaurant.presenter.splash

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.ClientRepository
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val clientRepository: ClientRepository
) : BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (clientRepository.getTableId().isNullOrEmpty()) {
            viewState.openNavigationActivity()
        } else {
            viewState.openMainActivity()
        }
    }

}