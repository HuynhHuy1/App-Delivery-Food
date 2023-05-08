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
import com.squareup.picasso.Picasso

class FoodManageAdapter(var listData : List<FoodModel>) : Adapter<FoodManageAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.name_order_adapter_admin)
        var image = view.findViewById<ImageView>(R.id.image_order_admin)
        var price = view.findViewById<TextView>(R.id.price_food_admin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.food_manage_adapter ,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Picasso.get().load(listData[position].image).into(holder.image)
        holder.name.setText(listData[position].name)
        holder.price.setText("$ ${listData[position].price}")
        holder.image.setImageResource(listData[position].image.toInt())
    }

}