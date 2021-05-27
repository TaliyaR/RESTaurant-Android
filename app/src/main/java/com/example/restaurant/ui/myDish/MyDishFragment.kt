package com.example.restaurant.ui.myDish

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.R
import com.example.restaurant.entities.Position
import com.example.restaurant.presenter.myDish.MyCookingDishPresenter
import com.example.restaurant.presenter.myDish.MyCookingDishView
import com.example.restaurant.ui.freeDish.rv.CookDishAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
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
class MyDishFragment : MvpAppCompatFragment(), MyCookingDishView {

    companion object {

        fun newInstance() = MyDishFragment()
    }

    @Inject
    lateinit var diPresenter: MyCookingDishPresenter

    @InjectPresenter
    lateinit var presenter: MyCookingDishPresenter

    @ProvidePresenter
    fun providePresenter() = diPresenter

    private var recyclerAdapter = CookDishAdapter { presenter.onDishCookedClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_dish, container, false)
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

    override fun confirmationDialog(position: Position) {
        MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialog)
            .setTitle(getString(R.string.my_dish_dialog_text))
            .setMessage(getString(R.string.my_dish_dialog_text))
            .setPositiveButton("Да") { dialog, _: Int ->
                presenter.dishCooked(position)
            }
            .setNegativeButton("Нет") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .show()
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