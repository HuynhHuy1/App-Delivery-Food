package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import android.util.Log
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
import com.example.fooddeliveryapp.view.customer.adapter.`interface`.handleOnClick

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
        var listTea = CategoryModel("Tea")
        var listCoffe =  CategoryModel("Coffee")
        var listFreeze = CategoryModel("Freeze")
        var listCake =  CategoryModel("Cake")
        var ListcategoryModel = listOf<CategoryModel>(
            listCoffe,
            listTea,listFreeze,listCake
        )
        var listFood = listOf<FoodModel>(
            FoodModel("Cappuccino", R.drawable.capucino1,"Coffee", 2.34),
            FoodModel("Espresso", R.drawable.cappuccino3,"Coffee", 2.36),
            FoodModel("Americano", R.drawable.cappuccino4,"Coffee", 2.21),
            FoodModel("Latte", R.drawable.capucino1,"Coffee", 2.31),
            FoodModel("Lotus Tea", R.drawable.capuccino2,"Tea", 1.52),
            FoodModel("Peach Tea",R.drawable.cappuccino3,"Tea", 2.64),
            FoodModel("Lychee Tea", R.drawable.cappuccino4,"Tea", 2.21),
            FoodModel("Green Tea",R.drawable.capucino1,"Tea", 1.23),
            FoodModel("Chocolate freeze",R.drawable.capuccino2,"Freeze", 1.52),
            FoodModel("Green tea freeze",R.drawable.cappuccino3,"Freeze", 2.16),
            FoodModel("Caramel freeze",R.drawable.cappuccino4,"Freeze", 2.29),
            FoodModel("Cookie freeze",R.drawable.cappuccino4,"Freeze", 2.29),
            FoodModel("Banana cake",R.drawable.capucino1,"Cake", 2.36),
            FoodModel("Tiramisu cake",R.drawable.capuccino2,"Cake", 2.12),
            FoodModel("Chocolate cake", R.drawable.cappuccino3,"Cake", 2.36),
            FoodModel("Caramel cake",R.drawable.cappuccino4,"Cake", 2.11),
            )
        addItemToCategory(listTea,listFood)
        addItemToCategory(listCoffe,listFood)
        addItemToCategory(listCake,listFood)
        addItemToCategory(listFreeze,listFood)
        Log.d("TAG", "${listTea.listFood[0].name}")
        Log.d("TAG", "${listCake.listFood[0].name}")
        Log.d("TAG", "${listCoffe.listFood[0].name}")
        Log.d("TAG", "${listFreeze.listFood[0].name}")
        var adapterCategory = CategoryAdapter(ListcategoryModel,object : handleOnClick{
            override fun onClickItem(toString: String) {

                for(category in ListcategoryModel){
                    if(toString == category.name){
                        Log.d("TAG", " ${toString}")
                        var adapterFood = FoodAdapter(category.listFood)
                        var rcvFood = view.findViewById<RecyclerView>(R.id.rcvFood)
                        rcvFood.adapter = adapterFood
                        rcvFood.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
                    }
                    
                }
            }

        })
        var adapterFood = FoodAdapter(ListcategoryModel[0].listFood)
        var rcvFood = view.findViewById<RecyclerView>(R.id.rcvFood)
        rcvFood.adapter = adapterFood
        rcvFood.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
        var rcvCategory = view.findViewById<RecyclerView>(R.id.linear_list_cate1)
        rcvCategory.adapter = adapterCategory
        rcvCategory.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)

        }
        fun addItemToCategory(category : CategoryModel, foodModel: List<FoodModel>) : List<FoodModel>{
            for(i in foodModel.indices step 1){
                foodModel[i].addToCategory(category)
            }
            return category.listFood
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