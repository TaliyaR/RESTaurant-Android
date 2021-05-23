package com.example.restaurant.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.ui.main.MainActivity
import com.example.restaurant.ui.navigation.NavigationActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, NavigationActivity::class.java))
        finish()
    }
}