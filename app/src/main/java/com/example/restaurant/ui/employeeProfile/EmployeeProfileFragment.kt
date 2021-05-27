package com.example.restaurant.ui.employeeProfile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.restaurant.R
import com.example.restaurant.entities.Employee
import com.example.restaurant.entities.RoleType
import com.example.restaurant.presenter.employeeProfile.EmployeeProfilePresenter
import com.example.restaurant.presenter.employeeProfile.EmployeeProfileView
import com.example.restaurant.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_employee_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeProfileFragment : MvpAppCompatFragment(), EmployeeProfileView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_profile, container, false)
    }

    @Inject
    lateinit var diPresenter: EmployeeProfilePresenter

    @InjectPresenter
    lateinit var presenter: EmployeeProfilePresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    companion object {

        fun newInstance() = EmployeeProfileFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener { presenter.logout() }
    }

    override fun setEmployeeInfo(employee: Employee) {
        tv_last_name.text = employee.lastName
        tv_first_name.text = employee.firstName
        tv_father_name.text = employee.fatherName
        tv_restaurant_name.text = employee.restaurant.name
        tv_role_name.text = when (employee.role) {
            RoleType.COOK.name -> getString(R.string.profile_employee_role_cook)
            else -> getString(R.string.profile_employee_role_waiter)
        }
    }

    override fun setProgressBar(boolean: Boolean) {
        progress_bar_back.isVisible = boolean
        progress_bar.isVisible = boolean
    }

    override fun openNavigationActivity() {
        startActivity(
            Intent(
                context,
                NavigationActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun showMessage(msg: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}