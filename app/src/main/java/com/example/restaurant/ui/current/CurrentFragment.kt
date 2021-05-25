package com.example.restaurant.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.R
import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.current.CurrentFragmentPresenter
import com.example.restaurant.presenter.current.CurrentView
import com.example.restaurant.ui.current.rv.OrderAdapter
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
    }

    override fun setTableInfo(number: String) {
        tv_table.text = number
    }

    override fun setTimeInfo(str: String) {
        tv_time.text = str
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
        recyclerAdapter.setList(list)
    }

}