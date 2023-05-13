package com.example.fooddeliveryapp.view

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.AccountModel
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.customer.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class registerActivity : AppCompatActivity() {
     private val auth: FirebaseAuth by lazy{
         FirebaseAuth.getInstance()
     }

    val progressDialog : ProgressDialog by lazy {
        ProgressDialog(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        onCLickSignUp()
    }
    fun onCLickSignUp(){
        val ed1 = findViewById<EditText>(R.id.tvUserNameRegister)
        val ed2 = findViewById<EditText>(R.id.tvPassWordRegister)
        val ed3 = findViewById<EditText>(R.id.tvConFirmRegister)
        val btnSignUp = findViewById<Button>(R.id.btnRegister)
        btnSignUp.setOnClickListener {
            Log.d("TAG", "onCLickSignUp: ")
            if(ed1.text.toString() == "" ||ed2.text.toString() == "" ||ed3.text.toString() == ""){
                Toast.makeText(this,"Please Enter In Full", Toast.LENGTH_SHORT).show()
            }
            else{
                if(ed2.text.toString() != ed3.text.toString()){
                    Toast.makeText(this,"Confirm Pass Wrong", Toast.LENGTH_SHORT).show()
                }
                else{
                    auth.createUserWithEmailAndPassword(ed1.text.toString(),ed2.text.toString())
                        .addOnCompleteListener(this) { task ->
                            progressDialog.show()
                            if (task.isSuccessful) {
                                progressDialog.dismiss()
                                val user = auth.currentUser
                                var intent = Intent(this, MainActivity::class.java)
                                var gson = Gson()
                                var newAcount = AccountModel(user!!.email,)
                                var accountJson = gson.toJson(newAcount,AccountModel::class.java)
                                intent.putExtra("account",accountJson)
                                ConfigFirebase().getAccountFromFireBase(newAcount.userName,newAcount)
                                startActivity(intent)
                                finish()
                            } else {
                                progressDialog.dismiss()
                                // If sign in fails, display a message to the user.
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                }
            }
        }
    }
}