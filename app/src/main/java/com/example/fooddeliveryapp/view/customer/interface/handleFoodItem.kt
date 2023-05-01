package com.example.fooddeliveryapp.view.customer.`interface`

import android.view.View
import com.example.fooddeliveryapp.model.FoodModel

interface handleFoodItem {
    fun clickAddFood(view: View, foodModel : FoodModel)
}