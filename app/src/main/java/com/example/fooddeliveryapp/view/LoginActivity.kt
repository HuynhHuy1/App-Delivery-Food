package com.example.fooddeliveryapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fooddeliveryapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)
        tvCreateAccount.setOnClickListener {
            startActivity(Intent(this,registerActivity::class.java))
        }
    }
}