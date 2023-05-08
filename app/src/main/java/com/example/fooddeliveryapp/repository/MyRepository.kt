package com.example.fooddeliveryapp.repository

import android.graphics.ColorSpace.Model
import com.example.fooddeliveryapp.model.FoodModel

interface MyRepository {
    suspend fun getModelFoodList() : List<FoodModel>
}