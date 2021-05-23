package com.example.restaurant.ui.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.R
import com.example.restaurant.ui.login.LoginActivity
import com.example.restaurant.ui.qr.QrActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_navigation.*

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        btn_login.setOnClickListener {
            this.startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_qr.setOnClickListener {
            this.startActivity(Intent(this, QrActivity::class.java))
        }
    }
}