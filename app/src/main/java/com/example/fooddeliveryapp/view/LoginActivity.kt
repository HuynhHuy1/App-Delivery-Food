package com.example.fooddeliveryapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.AccountModel
import com.example.fooddeliveryapp.view.admin.AdminMainActivity
import com.example.fooddeliveryapp.view.customer.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    val accountAdmin : AccountModel by lazy {
        AccountModel("admin","123")
    }
    val auth : FirebaseAuth by lazy{
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)
        tvCreateAccount.setOnClickListener {
            startActivity(Intent(this,registerActivity::class.java))
        }
        var tv1 = findViewById<EditText>(R.id.tvUserNameLogin)
        var tv2 = findViewById<EditText>(R.id.tvPassWordLogin)
        var loginBtn = findViewById<Button>(R.id.btnLogin)
        loginBtn.setOnClickListener {
            Log.d("TAG", "${tv1.text.toString()}")
            if(tv1.text.toString() == "" || tv2.text.toString() == ""){
                    Toast.makeText(this,"Please enter in full",Toast.LENGTH_SHORT).show()
            }
            else{
                if(tv1.text.toString() == accountAdmin.userName && tv2.text.toString() == accountAdmin.passWord){
                    startActivity(Intent(this,AdminMainActivity::class.java))
                }
                else{
                    auth.signInWithEmailAndPassword(tv1.text.toString(),tv2.text.toString())
                        .addOnCompleteListener {task ->
                            if(task.isSuccessful){
                                val user = auth.currentUser
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            }
                            else{
                                Toast.makeText(this,"User or Pass Wrong",Toast.LENGTH_SHORT).show()
                            }
                        }
                }

            }
        }
    }
}