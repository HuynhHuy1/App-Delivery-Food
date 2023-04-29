package com.example.fooddeliveryapp.view.customer.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.model.FoodModel
import org.w3c.dom.Text
import java.lang.reflect.Type
import java.util.zip.Inflater

class FoodAdapter(var listData : List<FoodModel> ) : Adapter<FoodAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.tv_name_food_adapter)
        var subName = view.findViewById<TextView>(R.id.tv_subname_food_adapter)
        var image = view.findViewById<ImageView>(R.id.image_food_adapter)
        var price = view.findViewById<TextView>(R.id.price_food_adapter)

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
        holder.subName.setText(listData[position].subName)
        var price = listData[position].price
        holder.price.setText("$ ${price}")
    }
}