package com.example.fooddeliveryapp.view.customer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.`interface`.handleAdd

class OrderAdapter(var listData : List<FoodModel>,var handleAdd: handleAdd ) : Adapter<OrderAdapter.viewHolder>() {
    class viewHolder(view :View ) : ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.name_order_adapter)
        var image = view.findViewById<ImageView>(R.id.image_order)
        var tvCount = view.findViewById<TextView>(R.id.count_item)
        var count = view.findViewById<TextView>(R.id.count_item).text.toString().toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.order_adapter ,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.distinct().size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        var listData2 = listData.distinct()
        holder.name.text = listData2[position].name
        listData.forEach{
            if(it.name == holder.name.text.toString()){
                Log.d("TAG", "count : ${holder.count}")
                holder.count = holder.count + 1
            }
        }
        holder.tvCount.text = "${holder.count -1}"
        listData2[position].image?.let { holder.image.setImageResource(it.toInt()) }
        holder.itemView.findViewById<Button>(R.id.addBtn).setOnClickListener {
            var countUpdate = holder.itemView.findViewById<TextView>(R.id.count_item).text.toString().toInt()
            countUpdate += 1
            holder.itemView.findViewById<TextView>(R.id.count_item).text = "${countUpdate}"
            handleAdd.handAddOnClick(listData2[position])
            Log.d("add 1", "${listData2[position].name} ")
        }
        holder.itemView.findViewById<Button>(R.id.subBtn).setOnClickListener {
            if(holder.count > 0){
                var countUpdate = holder.itemView.findViewById<TextView>(R.id.count_item).text.toString().toInt()
                countUpdate -= 1
                holder.itemView.findViewById<TextView>(R.id.count_item).text = "${countUpdate}"
                handleAdd.handSubOnClick(listData2[position])
                Log.d("add 2", "${listData2[position].name} ")
            }
        }
    }

}