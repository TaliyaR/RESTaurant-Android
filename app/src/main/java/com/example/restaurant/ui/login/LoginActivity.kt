package com.example.restaurant.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.restaurant.R
import com.example.restaurant.presenter.login.LoginActivityPresenter
import com.example.restaurant.presenter.login.LoginView
import com.example.restaurant.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatActivity
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : MvpAppCompatActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginActivityPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { }
        btn_register.setOnClickListener { presenter.onSignUpButtonClick() }
        btn_forgot.setOnClickListener { presenter.onForgotPasswordButtonClick() }

        topAppBar.setNavigationOnClickListener { finish() }
    }

    override fun showMessage(msg: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}