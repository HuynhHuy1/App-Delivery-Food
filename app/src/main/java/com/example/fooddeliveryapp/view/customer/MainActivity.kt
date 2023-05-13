package com.example.fooddeliveryapp.view.customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.AccountModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var newAccountJson = intent.getStringExtra("account")
        var gson = Gson()
        var newAccount = gson.fromJson(newAccountJson, AccountModel::class.java)
        loadUser(newAccount)
    }

    fun loadUser(accountModel: AccountModel) {
        ConfigFirebase().getProfileFromFirebae(accountModel) {
            var homeFragment = HomeFragment(accountModel, it)
            var orderFragment = OrderFragment(it)
            var oderStatus = StatusOrderFragment(it)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, homeFragment).commit()
            var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView_order)
            bottomnav.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment, homeFragment).commit()
                        true
                    }
                    R.id.order -> {
                        bottomnav.visibility = View.GONE
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment, orderFragment).commitNow()
                        true
                    }
                    R.id.orderstatus -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment, oderStatus)
                            .commitNow()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}
