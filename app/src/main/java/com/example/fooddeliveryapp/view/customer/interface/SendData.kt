package com.example.fooddeliveryapp.view.customer.`interface`

import android.graphics.ColorSpace.Model
import com.example.fooddeliveryapp.model.OrderModel

interface SendData {
    fun onSendData(order : OrderModel)
}