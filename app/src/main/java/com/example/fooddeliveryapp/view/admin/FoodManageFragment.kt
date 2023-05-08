package com.example.fooddeliveryapp.view.admin

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.adapter.FoodAdapter
import com.example.fooddeliveryapp.view.customer.adapter.FoodManageAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodManageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodManageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.food_manage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFood(view)
        val FAB = view.findViewById<FloatingActionButton>(R.id.fab_food_admin)
        FAB.setOnClickListener{
            handleOnClickFAB()
        }
    }
    fun handleOnClickFAB(){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_food, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()

        // handle OK button click
        dialogView.findViewById<Button>(R.id.btn_add_food).setOnClickListener {
            val editText = dialogView.findViewById<EditText>(R.id.ed_category_add_food)
            val inputText = editText.text.toString()
            // do something with the input text

            alertDialog.dismiss()
        }

        // handle Cancel button click
        dialogView.findViewById<Button>(R.id.btn_cancle_food).setOnClickListener {
            alertDialog.dismiss()
        }
    }
    fun setUpAdapter(view : View,dataListFood : List<FoodModel>){
        val args = arguments?.getString("Category","")
        val title = view.findViewById<TextView>(R.id.tile_food_manage)
        title.text = args
        var newList = dataListFood.filter { it.category == args }
        Log.d("TAG", "setUpAdapter: ${newList.size}")
        val adapterFood = FoodManageAdapter(newList)
        val rcvFood = view.findViewById<RecyclerView>(R.id.rcv_food_admin)
        rcvFood.adapter = adapterFood
        rcvFood.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
    }
    fun getFood(view: View){
        ConfigFirebase().firebaseReferenceFood {
            setUpAdapter(view,it)
        }
    }
}