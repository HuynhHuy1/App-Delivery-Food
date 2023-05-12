package com.example.fooddeliveryapp.model

class OrderModel(var id : Int,var address : String,var  foods : List<FoodModel>, var user : User,var  total : Double,var  statusOrder : String,var timeOrder : String) {

}