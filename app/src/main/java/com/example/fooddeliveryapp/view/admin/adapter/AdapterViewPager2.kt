package com.example.fooddeliveryapp.view.admin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fooddeliveryapp.view.admin.MenuFragment
import com.example.fooddeliveryapp.view.admin.Home1FragmentAdmin

class AdapterViewPager2(var fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                Home1FragmentAdmin()
            }
            1 -> {
                Home1FragmentAdmin()
            }
            else -> {
                Home1FragmentAdmin()
            }
        }
        }
}