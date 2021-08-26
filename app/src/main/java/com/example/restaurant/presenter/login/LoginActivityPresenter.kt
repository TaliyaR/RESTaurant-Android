package com.example.restaurant.presenter.login

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.AuthRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LoginActivityPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : BasePresenter<LoginView>() {

    fun onForgotPasswordButtonClick() {}

    fun onSignUpButtonClick() {}

    fun onSignInButtonClick(email: String, password: String) {
        launch {
            handleResult(authRepository.signIn(email, password), {
                viewState.mainCookScreenOpen()
            }, {
                handleError(it)
            })
        }
    }
}