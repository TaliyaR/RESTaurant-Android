package com.example.restaurant.viewmodel.first

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {}