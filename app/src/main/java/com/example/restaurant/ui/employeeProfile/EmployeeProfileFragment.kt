package com.example.restaurant.ui.employeeProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurant.R
import com.example.restaurant.entities.Employee
import com.example.restaurant.presenter.current.CurrentFragmentPresenter
import com.example.restaurant.presenter.current.CurrentView
import com.example.restaurant.presenter.employeeProfile.EmployeeProfilePresenter
import com.example.restaurant.presenter.employeeProfile.EmployeeProfileView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_employee_profile.*
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

    override fun setEmployeeInfo(employee: Employee) {
        tv_last_name.text = employee.lastName
        tv_first_name.text = employee.firstName
        tv_father_name.text = employee.fatherName
        tv_restaurant_name.text = employee.restaurant.name
        tv_role_name.text = employee.role
    }

    override fun showMessage(msg: String) {
    }
}