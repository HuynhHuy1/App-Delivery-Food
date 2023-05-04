package com.example.fooddeliveryapp.view.admin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.example.fooddeliveryapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminMainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        var MenuFragment = MenuFragment()
        var orderFragmnet = OrderFragmentAdmin()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentAdmin,orderFragmnet).commitNow()
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView_Admin)
        bottomnav.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.managerStatusFood -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentAdmin,orderFragmnet).commitNow()
                    true
                }
                R.id.managerMenu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentAdmin,MenuFragment).commitNow()
                    true
                }
                else -> false
            }
        }
        var fabAdmin = findViewById<FloatingActionButton>(R.id.fabAdmin)
        var fabAdminAdd = findViewById<FloatingActionButton>(R.id.fabAdminAdd1)
        var fabAdminCate = findViewById<FloatingActionButton>(R.id.fabAdminCate)
        fabAdmin.setOnClickListener{
            fabAdminAdd.show()
            fabAdminCate.show()
            fabAdminCate.setOnClickListener{
                Log.d("TAG", "Cate Click")
            }
            fabAdminAdd.setOnClickListener{
                Log.d("TAG", "Add")
            }
        }
    }
}