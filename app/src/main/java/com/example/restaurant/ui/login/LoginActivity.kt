package com.example.restaurant.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.R
import com.example.restaurant.ui.main.MainActivity
import com.example.restaurant.viewmodel.login.LoginActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { this.startActivity(Intent(this, MainActivity::class.java)) }
        btn_register.setOnClickListener { viewModel.onSignUpButtonClick() }
        btn_forgot.setOnClickListener { viewModel.onForgotPasswordButtonClick() }

        topAppBar.setNavigationOnClickListener { finish() }
    }
}