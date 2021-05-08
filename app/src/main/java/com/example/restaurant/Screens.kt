package com.example.restaurant

import androidx.fragment.app.Fragment
import com.example.restaurant.ui.first.FirstFragment
import com.example.restaurant.ui.login.LoginFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object FirstScreen : SupportAppScreen() {

        override fun getFragment(): Fragment? {
            return FirstFragment.newInstance()
        }
    }

    object LoginScreen : SupportAppScreen() {

        override fun getFragment(): Fragment? {
            return LoginFragment.newInstance()
        }
    }
}