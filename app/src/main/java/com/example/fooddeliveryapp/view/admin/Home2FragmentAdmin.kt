package com.example.fooddeliveryapp.view.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.model.OrderModel
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.admin.adapter.onClickOrder
import com.example.fooddeliveryapp.view.customer.adapter.OrderAdminAdapter
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

class Home2FragmentAdmin : Fragment() {
    val viewModel : SendDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }
    private val completeFragmentAdmin : MenuFragment by lazy {
        MenuFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment_admin2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listOrder = arrayListOf<OrderModel>(
            OrderModel("Da Nang",
                listOf(
                    FoodModel("Americano","https://firebasestorage.googleapis.com/v0/b/delivery-food-app-8f8a2.appspot.com/o/image_food%2Fcoffee%2Fcapucino1.png?alt=media&token=c01527fd-5272-4e32-b480-454f544822cd","Coffee",2.51),
                    FoodModel("Tea Lychee","https://firebasestorage.googleapis.com/v0/b/delivery-food-app-8f8a2.appspot.com/o/image_food%2Ftea%2FTea_lychee.jpeg?alt=media&token=d5c4b150-3af7-4a11-bcbe-fc098314a25a","Coffee",2.21),
                    FoodModel("Peach Tea","https://firebasestorage.googleapis.com/v0/b/delivery-food-app-8f8a2.appspot.com/o/image_food%2Ftea%2Fpeach_tea.jpeg?alt=media&token=3e505359-275e-4627-a335-a3f2f08415f5","Coffee",2.01),
                ),
                User("Huy",R.drawable.avt2,"Da nang"),
                2.01,
                "Complete",
                "2101"
            ),
            OrderModel("Da Nang",
                listOf(
                    FoodModel("Americano",R.drawable.coffee_americano.toString(),"Coffee",2.01),
                    FoodModel("Peach Tea",R.drawable.peach_tea.toString(),"Coffee",2.41),
                    FoodModel("Tea Lychee", R.drawable.avt2.toString(),"Coffee",2.11),
                ),
                User("Huy",R.drawable.avt2,"Da nang"),
                2.01,
                "Complete",
                "2101"
            ),

            OrderModel("Da Nang",
                listOf(
                    FoodModel("Huy",R.drawable.cake_caramel.toString(),"Coffee",2.01),
                    FoodModel("Huy",R.drawable.cake_caramel.toString(),"Coffee",2.01),
                    FoodModel("Huy",R.drawable.cake_caramel.toString(),"Coffee",2.01),
                ),
                User("Huy",R.drawable.avt2,"Da nang"),
                2.01,
                "Complete",
                "2101"
            )
        )
        var adapterOrderAdmin = OrderAdminAdapter(listOrder, object : onClickOrder{
            override fun handleOnClickConfirm(position: Int) {
                handleCLickConfirm(view)
            }

            override fun handleOnClickComplete(position : Int) {
                handleOnClickCompleteBtn(view)
            }

        })
        adapterOrderAdmin.notifyDataSetChanged()
        var rcvorderAdmin2 = view.findViewById<RecyclerView>(R.id.rcv_home_fragment_admin_2)
        rcvorderAdmin2.adapter = adapterOrderAdmin
        rcvorderAdmin2.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
    }

    private fun handleOnClickCompleteBtn(view: View) {
    }

    fun handleCLickConfirm(view : View){
    }
}