package com.example.fooddeliveryapp.view.customer.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.CategoryModel
import java.lang.reflect.Type
import java.util.zip.Inflater

class CategoryAdapter(var listData : List<CategoryModel> ) : Adapter<CategoryAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var textView = view.findViewById<TextView>(R.id.category_order_adapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.category_adapter ,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        if(position == 0){
            holder.textView.text = listData[position].name
            holder.textView.setBackgroundResource(R.drawable.background_button_selected_list_custommer)
            holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.white))
        }
        else{
            holder.textView.text = listData[position].name
        }

    }
}