package com.example.fooddeliveryapp.view.customer.adapter

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.fooddeliveryapp.view.admin.adapter.onClickOrder

class OrderAdminAdapter2(var listData: ArrayList<OrderModel>) : Adapter<OrderAdminAdapter2.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        val timeOrder = view.findViewById<TextView>(R.id.time_order_admin)
        val rcvFood = view.findViewById<RecyclerView>(R.id.rcv_status_food)
        val totalPayment = view.findViewById<TextView>(R.id.total_payment_status_admin)
        val confirmBtn = view.findViewById<TextView>(R.id.btn_admin_confirm)
        var complete  = view.findViewById<TextView>(R.id.btn_complete_admin)
        var infoName = view.findViewById<TextView>(R.id.tv_info_name)
        var infoAddress = view.findViewById<TextView>(R.id.tv_info_address)
        var infoPhone = view.findViewById<TextView>(R.id.tv_info_phone)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.order_admin_adapter,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.timeOrder.text = listData[position].timeOrder
        holder.infoName.text = listData[position].user.name
        holder.infoPhone.text = listData[position].user.phoneNumber
        holder.infoAddress.text = listData[position].user.address
        val layoutNestedRecyclerView = LinearLayoutManager(holder.rcvFood.context)
        layoutNestedRecyclerView.orientation = LinearLayoutManager.VERTICAL
        holder.rcvFood.layoutManager = layoutNestedRecyclerView
        val adapterOrder = FoodStatusAdapter(listData[position].foods)
        holder.rcvFood.adapter = adapterOrder
        holder.totalPayment.text ="$ ${String.format("%.02f",listData[position].total)}"
        holder.confirmBtn.visibility = View.GONE
        holder.complete.visibility = View.GONE

    }
}