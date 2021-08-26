package com.example.restaurant.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.restaurant.R
import com.example.restaurant.presenter.profile.ProfilePresenter
import com.example.restaurant.presenter.profile.ProfileView
import com.example.restaurant.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @Inject
    lateinit var diPresenter: ProfilePresenter

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    companion object {

        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener { presenter.onLogoutButtonClick() }
    }

    override fun showMessage(msg: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun openNavigationActivity() {
        startActivity(
            Intent(
                context,
                NavigationActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }
}