package com.example.fooddeliveryapp.model

import android.util.Log

class FoodModel(var name: String, var image: String, var category: String, var price: Double ) {
    fun addToCategory(categoryModel : CategoryModel){
        if(categoryModel.name == this.category){
            categoryModel.listFood.add(this)
            Log.d("TAG", "add ${this.name} to ${categoryModel.name}")
        }
        else{
            Log.d("TAG", "That bai")
        }
    }
}