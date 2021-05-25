package com.example.restaurant.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.restaurant.ui.navigation.NavigationActivity
import moxy.MvpAppCompatActivity

class SplashActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, NavigationActivity::class.java))
        finish()
    }
}