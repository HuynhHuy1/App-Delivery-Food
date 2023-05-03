package com.example.fooddeliveryapp.view.customer.`interface`

import android.graphics.ColorSpace.Model
import com.example.fooddeliveryapp.model.FoodModel

interface handleGetData {
    fun onHandleGetData(arrayListFood : ArrayList<FoodModel>) : ArrayList<FoodModel>
}