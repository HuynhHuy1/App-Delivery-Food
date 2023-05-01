package com.example.fooddeliveryapp.viewmodel

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.model.OrderModel

class SendDataViewModel : ViewModel() {
    val listItem : MutableLiveData<List<FoodModel>> by lazy{
        MutableLiveData<List<FoodModel>>() }
    val orderItem : MutableLiveData<OrderModel> by lazy{
        MutableLiveData<OrderModel>()
    }
    fun setListItem(listFood : List<FoodModel>){
        listItem.postValue(listFood)
    }
    fun setOrder(orderModel: OrderModel){
        orderItem.postValue(orderModel)
    }
}