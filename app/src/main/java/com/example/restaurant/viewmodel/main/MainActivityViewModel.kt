package com.example.restaurant.viewmodel.main

import androidx.lifecycle.ViewModel
import com.example.restaurant.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    init {
        router.newRootScreen(Screens.FirstScreen)
    }

    fun initViewModel() {}
}