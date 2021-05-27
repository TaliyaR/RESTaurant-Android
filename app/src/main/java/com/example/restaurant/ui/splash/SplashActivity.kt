package com.example.restaurant.ui.splash

import android.content.Intent
import com.example.restaurant.presenter.splash.SplashPresenter
import com.example.restaurant.presenter.splash.SplashView
import com.example.restaurant.ui.main.MainActivity
import com.example.restaurant.ui.mainCook.MainCookActivity
import com.example.restaurant.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : MvpAppCompatActivity(), SplashView {

    @Inject
    lateinit var diPresenter: SplashPresenter

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    override fun openNavigationActivity() {
        startActivity(Intent(this, NavigationActivity::class.java))
        finish()
    }

    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun openMainCookActivity() {
        startActivity(Intent(this, MainCookActivity::class.java))
        finish()
    }

    override fun showMessage(msg: String) {}
}