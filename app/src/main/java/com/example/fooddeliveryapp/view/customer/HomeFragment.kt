package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.adapter.CategoryAdapter
import com.example.fooddeliveryapp.view.customer.adapter.FoodAdapter

//import com.example.fooddeliveryapp.view.customer.adapter.FoodAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var ListcategoryModel = listOf<CategoryModel>(
            CategoryModel("Cappucino"),
            CategoryModel("Latte"),
            CategoryModel("Americano"),
            CategoryModel("Machiato"),
            CategoryModel("Cake"),
        )
        var listFood = listOf<FoodModel>(
            FoodModel("Cappuccino","with milk", R.drawable.capucino1,"Cappuccino", 2.34),
            FoodModel("Cappuccino","with chocolate", R.drawable.capuccino2,"Cappuccino", 2.52),
            FoodModel("Cappuccino","with brown sugar", R.drawable.cappuccino3,"Cappuccino", 2.36),
            FoodModel("Cappuccino","with white chocolate", R.drawable.cappuccino4,"Cappuccino", 2.21),
            FoodModel("Cappuccino","with white chocolate", R.drawable.cappuccino4,"Cappuccino", 2.21),
            FoodModel("Cappuccino","with white chocolate", R.drawable.cappuccino4,"Cappuccino", 2.21)
        )
        var adapterCategory = CategoryAdapter(ListcategoryModel)
        var rcvCategory = view.findViewById<RecyclerView>(R.id.linear_list_cate1)
        rcvCategory.adapter = adapterCategory
        rcvCategory.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)

        var adapterFood = FoodAdapter(listFood)
        var rcvFood = view.findViewById<RecyclerView>(R.id.rcvFood)
        rcvFood.adapter = adapterFood
        rcvFood.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
        }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}