package com.example.restaurant.ui.mainCook

import android.os.Bundle
import com.example.restaurant.R
import com.example.restaurant.presenter.mainCook.MainCookPresenter
import com.example.restaurant.presenter.mainCook.MainCookView
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
class MainCookActivity : MvpAppCompatActivity(), MainCookView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var diPresenter: MainCookPresenter

    @InjectPresenter
    lateinit var presenter: MainCookPresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    private val navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.containerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cook)

        navigation.selectedItemId = R.id.action_order

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_my_dish -> {
                    presenter.openMyDishScreen()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_free_dish -> {
                    presenter.openFreeDishScreen()
                    return@OnNavigationItemSelectedListener true

                }
                R.id.action_employee_profile -> {
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