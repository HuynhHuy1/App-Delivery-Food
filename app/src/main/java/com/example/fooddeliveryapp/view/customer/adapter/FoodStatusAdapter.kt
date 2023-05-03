package com.example.fooddeliveryapp.view.customer.adapter

import android.util.Log
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

class FoodStatusAdapter(var listData : List<FoodModel>) : Adapter<FoodStatusAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.name_food_status)
        var price = view.findViewById<TextView>(R.id.price_food_status)
        var countFood = view.findViewById<TextView>(R.id.count_food_status)
        var count = 0
        var image = view.findViewById<ImageView>(R.id.image_status_food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.food_status_adapter,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.distinct().size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.name.text = listData[position].name
        var listData2 = listData.distinct()
        holder.name.text = listData2[position].name
        listData.forEach{
            if(it.name == holder.name.text.toString()){
                holder.count += 1
            }
        }
        holder.countFood.text = "x ${holder.count}"

        holder.price.text = "$ ${String.format("%.02f",listData[position].price * holder.count)  }"
        holder.image.setImageResource(listData[position].image.toInt())
    }
}