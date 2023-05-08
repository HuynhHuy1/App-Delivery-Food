package com.example.fooddeliveryapp.view.customer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.view.admin.adapter.onClickItemMenu

class AdapterMenuAdmin(var listData : List<CategoryModel>,var onClick: onClickItemMenu) : Adapter<AdapterMenuAdmin.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var textView = view.findViewById<TextView>(R.id.tv_menu_admin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.menu_admin,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.textView.text = listData[position].name
        holder.itemView.setOnClickListener{
            onClick.handleOnClickItem(holder.textView.text.toString())
        }
    }

}