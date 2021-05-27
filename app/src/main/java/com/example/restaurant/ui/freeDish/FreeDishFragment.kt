package com.example.restaurant.ui.freeDish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.R
import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.freeDish.FreeDishPresenter
import com.example.restaurant.presenter.freeDish.FreeDishView
import com.example.restaurant.ui.freeDish.rv.CookDishAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_free_dish.*
import kotlinx.android.synthetic.main.fragment_free_dish.progress_bar
import kotlinx.android.synthetic.main.fragment_free_dish.progress_bar_back
import kotlinx.android.synthetic.main.fragment_free_dish.rv_order
import kotlinx.android.synthetic.main.fragment_free_dish.swiperefresh
import kotlinx.android.synthetic.main.fragment_free_dish.tv_empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class FreeDishFragment : MvpAppCompatFragment(), FreeDishView {

    companion object {

        fun newInstance() = FreeDishFragment()
    }

    @Inject
    lateinit var diPresenter: FreeDishPresenter

    @InjectPresenter
    lateinit var presenter: FreeDishPresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    private var recyclerAdapter = CookDishAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_free_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_order.layoutManager = LinearLayoutManager(context)
        rv_order.adapter = recyclerAdapter

        swiperefresh.setOnRefreshListener {
            presenter.updateList()
        }
    }

    override fun setProgressBar(boolean: Boolean) {
        progress_bar_back.isVisible = boolean
        progress_bar.isVisible = boolean
    }

    override fun setList(list: List<Position>) {
        if (list.isNullOrEmpty()) {
            tv_empty.isVisible = true
            iv_note.isVisible = true
        } else {
            recyclerAdapter.setList(list)
            tv_empty.isVisible = false
            iv_note.isVisible = false
        }
    }

    override fun confirmationDialog() {
        TODO("Not yet implemented")
    }

    override fun stopRefresh() {
        swiperefresh.isRefreshing = false
    }

    override fun showMessage(msg: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

}