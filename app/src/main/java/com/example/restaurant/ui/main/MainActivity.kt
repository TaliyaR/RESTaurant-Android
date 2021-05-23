package com.example.restaurant.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.R
import com.example.restaurant.viewmodel.main.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.containerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.initViewModel()
        navigation.selectedItemId = R.id.action_order

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_order -> {
                    viewModel.openOrderScreen()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_history -> {
                    viewModel.openHistoryScreen()
                    return@OnNavigationItemSelectedListener true

                }
                R.id.action_profile -> {
                    viewModel.openProfileScreen()
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
}