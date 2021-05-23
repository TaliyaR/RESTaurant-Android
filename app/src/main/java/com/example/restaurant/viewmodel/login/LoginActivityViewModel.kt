package com.example.restaurant.viewmodel.login

import androidx.lifecycle.ViewModel
import com.example.restaurant.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
) : ViewModel() {

    fun onForgotPasswordButtonClick() {}

    fun onSignUpButtonClick() {}
}