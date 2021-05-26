package com.example.restaurant.ui.current

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.R
import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.current.CurrentFragmentPresenter
import com.example.restaurant.presenter.current.CurrentView
import com.example.restaurant.ui.current.rv.OrderAdapter
import com.example.restaurant.ui.navigation.NavigationActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_current.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class CurrentFragment : MvpAppCompatFragment(), CurrentView {

    companion object {

        fun newInstance() = CurrentFragment()
    }

    @Inject
    lateinit var diPresenter: CurrentFragmentPresenter

    @InjectPresenter
    lateinit var presenter: CurrentFragmentPresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    private var recyclerAdapter = OrderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_order.layoutManager = LinearLayoutManager(context)
        rv_order.adapter = recyclerAdapter

        presenter.getOrderByTable()

        swiperefresh.setOnRefreshListener {
            presenter.updateList()
        }
    }

    override fun setTableInfo(number: String) {
        tv_table.text = number
    }

    override fun setTimeInfo(str: String) {
        tv_time.text = " $str"
    }

    override fun setWaiterInfo(str: String) {
        tv_waiter.text = str
    }

    override fun showMessage(msg: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setOrderList(list: List<Position>) {
        if (list.isNullOrEmpty()) {
            tv_empty.isVisible = true
            iv_menu.isVisible = true
            tv_total.isVisible = false
            tv_total_header.isVisible = false
            tv_total_rub.isVisible = false
            divider.isVisible = false
        } else {
            recyclerAdapter.setList(list)
            tv_empty.isVisible = false
            iv_menu.isVisible = false
            tv_total.isVisible = true
            tv_total_header.isVisible = true
            tv_total_rub.isVisible = true
            divider.isVisible = true
        }
    }

    override fun setTotal(total: Int) {
        tv_total.text = " ${total}"
    }

    override fun stopRefresh() {
        swiperefresh.isRefreshing = false
    }

    override fun thanksDialog() {
        MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialog)
            .setMessage("Ваш заказ закрыт! Спасибо за посещение ресторана! Будем рады видеть Вас снова!")
            .setPositiveButton("Ок") { dialog, _: Int ->
                presenter.cleanId()
                startActivity(Intent(activity, NavigationActivity::class.java))
            }
            .show()
    }

    override fun setProgressBar(boolean: Boolean) {
        progress_bar_back.isVisible = boolean
        progress_bar.isVisible = boolean
    }
}