package com.example.restaurant.ui.main

import android.os.Bundle
import com.example.restaurant.R
import com.example.restaurant.presenter.login.LoginActivityPresenter
import com.example.restaurant.presenter.main.MainActivityPresenter
import com.example.restaurant.presenter.main.MainView
import com.example.restaurant.presenter.qr.QrActivityPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var diPresenter: MainActivityPresenter

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    private val navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.containerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.selectedItemId = R.id.action_order

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_order -> {
                    presenter.openOrderScreen()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_history -> {
                    presenter.openHistoryScreen()
                    return@OnNavigationItemSelectedListener true

                }
                R.id.action_profile -> {
                    presenter.openProfileScreen()
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        })
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun showMessage(msg: String) {}
}