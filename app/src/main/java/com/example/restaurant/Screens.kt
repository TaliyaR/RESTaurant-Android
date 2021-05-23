package com.example.restaurant

import androidx.fragment.app.Fragment
import com.example.restaurant.ui.current.CurrentFragment
import com.example.restaurant.ui.history.HistoryFragment
import com.example.restaurant.ui.profile.ProfileFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object CurrentScreen : SupportAppScreen() {

        override fun getFragment(): Fragment? {
            return CurrentFragment.newInstance()
        }
    }

    object ProfileScreen : SupportAppScreen() {

        override fun getFragment(): Fragment? {
            return ProfileFragment.newInstance()
        }
    }

    object HistoryScreen : SupportAppScreen() {

        override fun getFragment(): Fragment? {
            return HistoryFragment.newInstance()
        }
    }
}