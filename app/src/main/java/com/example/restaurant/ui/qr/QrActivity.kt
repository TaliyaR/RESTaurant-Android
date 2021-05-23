package com.example.restaurant.ui.qr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.R
import kotlinx.android.synthetic.main.activity_qr.*

class QrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        topAppBar.setNavigationOnClickListener { finish() }
    }
}