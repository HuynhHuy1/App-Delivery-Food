package com.example.fooddeliveryapp.view.admin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.view.LoginActivity
import com.example.fooddeliveryapp.view.admin.adapter.AdapterViewPager2
import com.google.android.material.navigation.NavigationView
import kotlin.concurrent.fixedRateTimer

class AdminMainActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        var viewPager2 = findViewById<ViewPager2>(R.id.myViewpager)
        var tv1 = findViewById<TextView>(R.id.tv_menu_inProgress)
        var tv2 = findViewById<TextView>(R.id.tv_menu_complete)
        var adapterViewPager2 = AdapterViewPager2(this)
        viewPager2.adapter = adapterViewPager2
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            @SuppressLint("ResourceAsColor")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                    when(position){
                        0 -> {
                           tv1.setTextColor(resources.getColor(R.color.teal_700));
                            tv2.setTextColor(resources.getColor(R.color.white));

                            Log.d("TAG", "onPageSelected: ${position}")
                        }
                        1 -> {
                            tv1.setTextColor(getResources().getColor(R.color.white));
                            tv2.setTextColor(getResources().getColor(R.color.teal_700));
                            Log.d("TAG", "onPageSelected: ${position}")
                        }
                    }
            }
        }
        )
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawNavi = findViewById<DrawerLayout>(R.id.drawerLayout_admin1)
        var accBarToggle = ActionBarDrawerToggle(this, drawNavi,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawNavi.addDrawerListener(accBarToggle)
        accBarToggle.syncState()
        handleClickItem()
    }
    private fun handleClickItem(){
        var menuFragment = MenuFragment()
        var updateFragmentAdmin = FoodManageFragment()
        val navigationView = findViewById<NavigationView>(R.id.drawerLayout_admin_home)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.manageMenuNavi -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_admin,menuFragment).addToBackStack(menuFragment.javaClass.simpleName).commit()
                    Log.d("TAG", "handleClickItem: ")
                    true
                }
                R.id.manageShopNavi2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_admin,updateFragmentAdmin).commit()
                    Log.d("TAG", "handleClickItem:1 ")
                    true
                }
                R.id.logoutAdmin -> {
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true

                }
                else -> false
            }

    }
}
}