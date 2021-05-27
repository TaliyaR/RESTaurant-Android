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
import kotlinx.android.synthetic.main.fragment_free_dish.iv_note
import kotlinx.android.synthetic.main.fragment_free_dish.progress_bar
import kotlinx.android.synthetic.main.fragment_free_dish.progress_bar_back
import kotlinx.android.synthetic.main.fragment_free_dish.rv_order
import kotlinx.android.synthetic.main.fragment_free_dish.swiperefresh
import kotlinx.android.synthetic.main.fragment_free_dish.tv_empty
import kotlinx.android.synthetic.main.fragment_my_dish.*
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

    private var recyclerAdapter = CookDishAdapter { presenter.onAddCookingClick(it) }

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
        recyclerAdapter.setList(list)
    }

    override fun showEmptyState(boolean: Boolean) {
        rv_order.isVisible = !boolean
        tv_empty.isVisible = boolean
        iv_note.isVisible = boolean
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