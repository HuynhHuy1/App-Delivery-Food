package com.example.fooddeliveryapp.view.customer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.OrderModel

class OrderStatusAdapter(var listData: List<OrderModel>) : Adapter<OrderStatusAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var timeOrder = view.findViewById<TextView>(R.id.time_order)
        var rcvFood = view.findViewById<RecyclerView>(R.id.rcv_status_food)
        var totalPayment = view.findViewById<TextView>(R.id.total_payment_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.status_adapter,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.timeOrder.text = listData[position].timeOrder
        val layoutNestedRecyclerView = LinearLayoutManager(holder.rcvFood.context)
        layoutNestedRecyclerView.orientation = LinearLayoutManager.VERTICAL
        holder.rcvFood.layoutManager = layoutNestedRecyclerView
        val adapterOrder = FoodStatusAdapter(listData[position].foods)
        holder.rcvFood.adapter = adapterOrder
        holder.totalPayment.text ="$ ${String.format("%.02f",listData[position].total)}"
    }
    
}