package com.example.fooddeliveryapp.view.customer

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.model.OrderModel
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.customer.adapter.OrderAdapter
import com.example.fooddeliveryapp.view.customer.`interface`.handleAdd
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel
import java.time.LocalDateTime

class OrderFragment(user: User) : Fragment() {
    val viewModel : SendDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }
    var priceDeliveryNumber = 1.15
    var price = 0.0
    lateinit var orderModel: OrderModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user1  = User("","Huy",R.drawable.avt2.toString(),"234 Le Trong Tan","0935484164")
        var rcvOrder = view.findViewById<RecyclerView>(R.id.linear3)
        viewModel.listItem.observe(viewLifecycleOwner, Observer {
            var adapterOrder = OrderAdapter(it,object : handleAdd{
                override fun handAddOnClick(foodModel: FoodModel) {
                    var tempList = it.toMutableList()
                    tempList.add(foodModel)
                    viewModel.listItem.postValue(tempList.toList())
                    Log.d("List 10","${it.size}")
                }

                override fun handSubOnClick(foodModel: FoodModel) {
                    var tempList = it.toMutableList()
                    tempList.remove(foodModel)
                    viewModel.listItem.postValue(tempList.toList())
                    Log.d("List 11","${it.size}")

                }

            })
            setInfoOrder(view,user1,it)
            rcvOrder.adapter = adapterOrder
            rcvOrder.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            rcvOrder.setOnClickListener {
                view.findViewById<ConstraintLayout>(R.id.layout_order).visibility = View.GONE
            }

        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setInfoOrder(view: View, user: User, listData : List<FoodModel>) {
        price = 0.0
        listData.forEach{
            Log.d("TAG", "for each : ${listData.size} ")
           price += it.price
        }
        var addressUser = view.findViewById<TextView>(R.id.address_orderr)
        addressUser.text = user.address
        var priceFood = view.findViewById<TextView>(R.id.tv_order_food_number)
        priceFood.text = "$ ${String.format("%.2f",price)}"
        var priceDeliviry = view.findViewById<TextView>(R.id.price_delivery_order_number)

        priceDeliviry.text = "$ ${1.15}"
        var total = view.findViewById<TextView>(R.id.tv_total_number_order)
        total.text = "$ ${String.format("%.2f",price + priceDeliveryNumber)}"
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleOrder(user: User, listData : List<FoodModel>, view: View){
        var id = (0..100000).random()
        var statusOrderFragment = StatusOrderFragment(User("","","","","",))
        val timerOrder = LocalDateTime.now()
        val hour = timerOrder.hour
        val minute = timerOrder.minute
        val day = timerOrder.dayOfMonth
        val motnh = timerOrder.monthValue
        val year = timerOrder.year
        val time = "${day}/${motnh}/${year}, ${hour}:${minute}"
        var total = price + priceDeliveryNumber
        orderModel = OrderModel(id,user.address,listData,user,total,"Confirm",time)
        ConfigFirebase().addOrderToFireBase(orderModel,requireActivity())
        Log.d("Order", "order thanh cong")
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment,statusOrderFragment).commit()
    }

}