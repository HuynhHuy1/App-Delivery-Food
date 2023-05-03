package com.example.fooddeliveryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.os.persistableBundleOf
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.HomeFragment
import com.example.fooddeliveryapp.view.customer.OrderFragment
import com.example.fooddeliveryapp.view.customer.StatusOrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var homeFragment = HomeFragment()
        var orderFragment = OrderFragment()
        var oderStatus = StatusOrderFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragment,homeFragment).commit()
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView_order)
        bottomnav.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment,homeFragment).commitNow()
//                    var fragment = binding.bottomNavigationView
                    true
                }
                R.id.order -> {
                    bottomnav.visibility = View.GONE
                    supportFragmentManager.beginTransaction().replace(R.id.fragment,orderFragment).commitNow()
                    true
                }
                R.id.orderstatus -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment,oderStatus).commitNow()
                    true
                }
                else -> false
            }
        }
    }
}
