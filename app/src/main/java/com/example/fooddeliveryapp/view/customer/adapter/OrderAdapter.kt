package com.example.fooddeliveryapp.view.customer.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.model.OrderModel
import com.example.fooddeliveryapp.view.customer.`interface`.handleOnClick
import org.w3c.dom.Text
import java.lang.reflect.Type
import java.util.zip.Inflater

class OrderAdapter(var listData : List<FoodModel> ) : Adapter<OrderAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.name_order_adapter)
        var image = view.findViewById<ImageView>(R.id.image_order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.order_adapter ,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.name.text = listData[position].name
        holder.image.setImageResource(listData[position].image)
        holder.itemView.findViewById<Button>(R.id.addBtn).setOnClickListener {
            var count =holder.itemView.findViewById<TextView>(R.id.count_item).text.toString().toInt()
            count = count + 1
            holder.itemView.findViewById<TextView>(R.id.count_item).text = "${count}"
        }
        holder.itemView.findViewById<Button>(R.id.subBtn).setOnClickListener {
            var count =holder.itemView.findViewById<TextView>(R.id.count_item).text.toString().toInt()
            if(count > 0){
                count = count - 1
                holder.itemView.findViewById<TextView>(R.id.count_item).text = "${count}"
            }
        }
    }

}