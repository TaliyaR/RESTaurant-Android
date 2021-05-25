package com.example.restaurant.presenter.login

import androidx.lifecycle.ViewModel
import com.example.restaurant.presenter.BasePresenter
import dagger.hilt.android.lifecycle.HiltViewModel
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LoginActivityPresenter @Inject constructor(
) : BasePresenter<LoginView>() {

    fun onForgotPasswordButtonClick() {}

    fun onSignUpButtonClick() {}
}