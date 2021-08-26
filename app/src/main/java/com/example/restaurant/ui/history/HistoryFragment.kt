package com.example.restaurant.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurant.R
import moxy.MvpAppCompatFragment


class HistoryFragment : MvpAppCompatFragment() {

    companion object {

        fun newInstance() = HistoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
}