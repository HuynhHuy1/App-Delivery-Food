package com.example.fooddeliveryapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.view.customer.MainActivity
import com.google.firebase.auth.FirebaseAuth

class introActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro)
        var button = findViewById<Button>(R.id.buttonIntro)
        button.setOnClickListener{
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

}