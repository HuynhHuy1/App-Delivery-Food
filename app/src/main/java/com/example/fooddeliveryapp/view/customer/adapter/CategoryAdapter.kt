package com.example.fooddeliveryapp.view.customer.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.`interface`.handleFoodItem
import com.example.fooddeliveryapp.view.customer.`interface`.handleOnClick
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

class CategoryAdapter(
    private var listData: List<CategoryModel>,
    var handleOnClick: handleOnClick,
    var rcvFood: RecyclerView,var viewModel: SendDataViewModel
): Adapter<CategoryAdapter.viewHolder>() {

    class viewHolder(view :View ) : ViewHolder(view){
        var textView = view.findViewById<TextView>(R.id.category_order_adapter)
        var isClicked = -1
        var isActive = false
        var view = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.category_adapter ,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
            if (position == holder.isClicked) {
                holder.textView.text = listData[position].name
                holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.white))
                holder.textView.setBackgroundResource(R.drawable.background_button_selected_list_custommer)
                Log.d("Tag if", "${position}")
                holder.isClicked = -1
            }
            else {
                    holder.textView.text = listData[position].name
                    holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.black))
                    holder.textView.setBackgroundResource(R.drawable.background_button_list_custommer)
                    Log.d("Tag else", "${position}")
                if(position == 0){
                    if(!holder.isActive){
                        holder.textView.text = listData[position].name
                        holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.white))
                        holder.textView.setBackgroundResource(R.drawable.background_button_selected_list_custommer)
                        holder.isClicked = position -1
                        handleOnClickItem(holder,listData[position].name)
                    }
                }

            }
                holder.itemView.setOnClickListener {
                holder.isClicked = position // Cập nhật vị trí item được chọn
                handleOnClick.onClickItem(holder.textView.text.toString())
                notifyDataSetChanged()
                    holder.isActive = true
                    handleOnClickItem(holder,listData[position].name)
                }
    }
    fun handleOnClickItem(holder : viewHolder,category : String){
        ConfigFirebase().firebaseReferenceFood {
            var newList = it.filter {
                it.category== category
            }
            var listAddItem = ArrayList<FoodModel>()
            val adapterFood = FoodAdapter(newList,object : handleFoodItem{
                override fun clickAddFood(view: View, foodModel: FoodModel) {
                    handleAddItemHome(view,listAddItem,foodModel)
                }
            })
            rcvFood.adapter = adapterFood
            rcvFood.adapter = adapterFood
            rcvFood.layoutManager = GridLayoutManager(holder.view.context,2,GridLayoutManager.VERTICAL,false)
        }
    }
    private fun handleAddItemHome(view: View, listAddItem : ArrayList<FoodModel>,foodModel: FoodModel){
        listAddItem.add(foodModel)
        viewModel.setListItem(listAddItem)
        Toast.makeText(view.context,"Add Success", Toast.LENGTH_SHORT).show()
    }
}