package com.example.fooddeliveryapp.view.customer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.`interface`.handleFoodItem

class FoodAdapter(var listData : List<FoodModel>,var handleFoodItem: handleFoodItem) : Adapter<FoodAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.tv_name_food_adapter)
        var image = view.findViewById<ImageView>(R.id.image_food_adapter)
        var price = view.findViewById<TextView>(R.id.price_food_adapter)
        var btnAddFood = view.findViewById<TextView>(R.id.btn_addFood_home)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.food_adapter ,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.image.setImageResource(listData[position].image)
        holder.name.setText(listData[position].name)
        var price = listData[position].price
        var foodModel = listData[position]
        holder.price.setText("$ ${price}")
        holder.btnAddFood.setOnClickListener{
            handleFoodItem.clickAddFood(holder.itemView,foodModel)
        }
    }
}