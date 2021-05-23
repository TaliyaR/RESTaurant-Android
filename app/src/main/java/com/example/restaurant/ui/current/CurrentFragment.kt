package com.example.restaurant.ui.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurant.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentFragment : Fragment() {

    companion object {

        fun newInstance() = CurrentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

}