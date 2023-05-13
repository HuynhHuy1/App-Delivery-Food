package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.AccountModel
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.customer.adapter.CategoryAdapter
import com.example.fooddeliveryapp.view.customer.`interface`.handleOnClick
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso

//import com.example.fooddeliveryapp.view.customer.adapter.FoodAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment(var newAccount: AccountModel,var  user: User) : Fragment() {
    private val viewModel : SendDataViewModel by lazy{
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUser(view)
        setCategoryAdapter(view)
        var drawLayout = view.findViewById<DrawerLayout>(R.id.drawerLayout_profile1)
        view.findViewById<ImageView>(R.id.image_info).setOnClickListener {
            drawLayout.openDrawer(GravityCompat.END)
        }
        handleOnClickItem(view)
    }


    private fun setCategoryAdapter(view: View){
        ConfigFirebase().getCategoryFromFirebase {
            var rcvFood = requireActivity().findViewById<RecyclerView>(R.id.rcvFood)
            var adapterCategory = CategoryAdapter(it,object : handleOnClick {
                override fun onClickItem(toString: String) {
                }
            },rcvFood,viewModel)
            var rcvCategory = view.findViewById<RecyclerView>(R.id.linear_list_cate1)
            rcvCategory.adapter = adapterCategory
            rcvCategory.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private fun setUser(view : View){
        var avt = view.findViewById<ImageView>(R.id.image_info)
        if(user.userName == ""){
            avt.setImageResource(R.drawable.baseline_person_24)
        }
        else{
            Picasso.get().load(user.avatar).into(avt)
        }


    }
    private fun handleOnClickItem(view : View ){
        val navigationView = view.findViewById<NavigationView>(R.id.drawerLayout_profile)
        var profileFragment = profileFragment(newAccount,user)
        navigationView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.UpdateMenu -> {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment,profileFragment).commitNow()
                   true
                }
                else -> {
                    false
                }
            }
        }
    }
}