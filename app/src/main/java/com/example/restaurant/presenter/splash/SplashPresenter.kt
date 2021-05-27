package com.example.restaurant.presenter.splash

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.AuthRepository
import com.example.restaurant.repositories.ClientRepository
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val clientRepository: ClientRepository,
    private val authRepository: AuthRepository
) : BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (authRepository.isSignedIn()) {
            viewState.openMainCookActivity()
        } else if (!clientRepository.getTableId().isNullOrEmpty()) {
            viewState.openMainActivity()
        } else {
            viewState.openNavigationActivity()
        }
    }
}