package com.example.fooddeliveryapp.view.customer

import android.content.Context
import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.model.OrderModel
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.customer.adapter.CategoryAdapter
import com.example.fooddeliveryapp.view.customer.adapter.OrderAdapter
import com.example.fooddeliveryapp.view.customer.`interface`.SendData
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import java.util.zip.Inflater

class OrderFragment : Fragment() {
    val viewModel : SendDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }

    lateinit var orderModel: OrderModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user1  = User("Huy",R.drawable.avt2,"234 Le Trong Tan")
        var rcvOrder = view.findViewById<RecyclerView>(R.id.linear3)
        viewModel.listItem.observe(viewLifecycleOwner, Observer {
            var adapterOrder = OrderAdapter(it)
            rcvOrder.adapter = adapterOrder
            rcvOrder.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            rcvOrder.setOnClickListener {
                view.findViewById<ConstraintLayout>(R.id.layout_order).visibility = View.GONE
            }

            setInfoOrder(view,user1,it)

        })
    }
    fun setInfoOrder(view: View,user: User,listData : List<FoodModel>) {
        var addressUser = view.findViewById<TextView>(R.id.address_orderr)
        addressUser.text = user.address
        var priceFood = view.findViewById<TextView>(R.id.tv_order_food_number)
        priceFood.text = "$ ${5.43}"
        var priceDeliviry = view.findViewById<TextView>(R.id.tv_total_number_order)
        priceDeliviry.text = "$ ${4.15}"
        var btnOrder = view.findViewById<TextView>(R.id.btnOrder)
        btnOrder.setOnClickListener{
            handleOrder(user,listData,view)
        }
        handleEditAddress(view)
    }
    fun handleEditAddress(view : View){
        var btnEditAddress = view.findViewById<Button>(R.id.btn_editAddress)

        btnEditAddress.setOnClickListener {
            Log.d("Tag", "Edit on Click ")
        }
    }
    fun handleOrder(user: User,listData : List<FoodModel>,view: View){
        var statusOrderFragment = StatusOrderFragment()
        orderModel = OrderModel(user.address,listData,user,1.3,"Complete")
        viewModel.setOrder(orderModel)
        Log.d("Order", "order thanh cong")
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment,statusOrderFragment).commit()
    }

}