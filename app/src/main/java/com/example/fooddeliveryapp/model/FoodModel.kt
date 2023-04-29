package com.example.fooddeliveryapp.model

class FoodModel(var name: String, var subName: String, var image: Int, var category: String, var price: Double ) {
    var id = 0
    fun addToCategory(categoryModel : CategoryModel){
        if(categoryModel.name.equals(this.category) ){
            categoryModel.listFood.add(this)
        }
    }
}