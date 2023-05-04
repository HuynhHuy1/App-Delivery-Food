package com.example.fooddeliveryapp.view.customer

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.adapter.CategoryAdapter
import com.example.fooddeliveryapp.view.customer.adapter.FoodAdapter
import com.example.fooddeliveryapp.view.customer.`interface`.handleFoodItem
import com.example.fooddeliveryapp.view.customer.`interface`.handleOnClick
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

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
    private val viewModel : SendDataViewModel by lazy{
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
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
        var configFirebase = ConfigFirebase()
        configFirebase.firebaseReferenceFood{
            var list = it.toList()
            var listData = loadData(list)
            var listTea = CategoryModel("Tea")
            var listCoffe =  CategoryModel("Coffee")
            var listFreeze = CategoryModel("Freeze")
            var listCake =  CategoryModel("Cake")
            var listAddItem = arrayListOf<FoodModel>()
            var ListcategoryModel = listOf<CategoryModel>(
                listCoffe,
                listTea,listFreeze,listCake
            )
            addItemToCategory(listTea,listData)
            addItemToCategory(listCoffe,listData)
            addItemToCategory(listCake,listData)
            addItemToCategory(listFreeze,listData)
            var adapterCategory = CategoryAdapter(ListcategoryModel,object : handleOnClick {
                override fun onClickItem(toString: String) {
                    clickItemCategory(ListcategoryModel,toString,view,object : handleFoodItem {
                        override fun clickAddFood(view: View, foodModel: FoodModel) {
                            handleAddItemHome(view,listAddItem,foodModel)
                            Log.d("List Add Item", "${1} : ${listAddItem[0].name}" )
                        }
                    })
                }
            })
            var adapterFood = FoodAdapter(ListcategoryModel[0].listFood,object : handleFoodItem {
                override fun clickAddFood(view: View, foodModel: FoodModel) {
                    handleAddItemHome(view,listAddItem,foodModel)
                    Log.d("List Add Item", "${1} : ${listAddItem[0].name}" )
                }

            })
            onClickInfo(view)
            setAdapter(view,adapterFood,adapterCategory)
        }


    }
    private fun handleAddItemHome(view: View, listAddItem : ArrayList<FoodModel>,foodModel: FoodModel){
        listAddItem.add(foodModel)
        viewModel.setListItem(listAddItem)
        Toast.makeText(view.context,"Add Success",Toast.LENGTH_SHORT).show()
    }

    private fun addItemToCategory(category : CategoryModel, foodModel: List<FoodModel>) : List<FoodModel>{
            for(i in foodModel.indices step 1){
                foodModel[i].addToCategory(category)
            }
            return category.listFood
        }

       private fun clickItemCategory(
            listCategory: List<CategoryModel>,
            string: String,
            view: View,
            param: handleFoodItem
        ){
            for(category in listCategory){
                if(string == category.name){
                    Log.d("TAG", " ${string}")
                    var adapterFood = FoodAdapter(category.listFood,param)
                    var rcvFood = view.findViewById<RecyclerView>(R.id.rcvFood)
                    rcvFood.adapter = adapterFood
                    rcvFood.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
                }
            }
        }
       private fun setAdapter(view : View, adapterFood: FoodAdapter,adapterCategory: CategoryAdapter){
            var rcvFood = view.findViewById<RecyclerView>(R.id.rcvFood)
            rcvFood.adapter = adapterFood
            rcvFood.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
            var rcvCategory = view.findViewById<RecyclerView>(R.id.linear_list_cate1)
            rcvCategory.adapter = adapterCategory
            rcvCategory.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        }
        private fun onClickInfo(view : View){
            var profileFragment = profileFragment()
            var imageInfo = view.findViewById<ImageView>(R.id.image_info)
            imageInfo.setOnClickListener{
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment,profileFragment).commit()
            }
        }

        private fun loadData(list : List<FoodModel>) : List<FoodModel>{
            return listOf(
                FoodModel("${list[0].name}", list[0].image,"${list[0].category}", list[0].price),
                FoodModel("${list[1].name}",list[1].image,"${list[1].category}", list[0].price),
                FoodModel("${list[2].name}", list[2].image,"${list[2].category}", list[0].price),
                FoodModel("${list[0].name}", list[0].image,"${list[0].category}", list[0].price),
                FoodModel("${list[0].name}", list[1].image,"${list[0].category}", list[0].price),
                FoodModel("${list[0].name}", list[2].image,"${list[0].category}", list[0].price),
                FoodModel("${list[0].name}", list[0].image,"${list[0].category}", list[0].price),
                FoodModel("${list[0].name}", list[1].image,"${list[0].category}", list[0].price),
//                FoodModel("Espresso", R.drawable.cappuccino3.toString(),"Coffee", 2.36),
//                FoodModel("Americano", R.drawable.cappuccino4.toString(),"Coffee", 2.21),
//                FoodModel("Latte", R.drawable.capucino1.toString(),"Coffee", 2.31),
//                FoodModel("Lotus Tea", R.drawable.capuccino2.toString(),"Tea", 1.52),
//                FoodModel("Peach Tea",R.drawable.cappuccino3.toString(),"Tea", 2.64),
//                FoodModel("Lychee Tea", R.drawable.cappuccino4.toString(),"Tea", 2.21),
//                FoodModel("Green Tea",R.drawable.capucino1.toString(),"Tea", 1.23),
//                FoodModel("Chocolate freeze",R.drawable.capuccino2.toString(),"Freeze", 1.52),
//                FoodModel("Green tea freeze",R.drawable.cappuccino3.toString(),"Freeze", 2.16),
//                FoodModel("Caramel freeze",R.drawable.cappuccino4.toString(),"Freeze", 2.29),
//                FoodModel("Cookie freeze",R.drawable.cappuccino4.toString(),"Freeze", 2.29),
//                FoodModel("Banana cake",R.drawable.capucino1.toString(),"Cake", 2.36),
//                FoodModel("Tiramisu cake",R.drawable.capuccino2.toString(),"Cake", 2.12),
//                FoodModel("Chocolate cake", R.drawable.cappuccino3.toString(),"Cake", 2.36),
//                FoodModel("Caramel cake",R.drawable.cappuccino4.toString(),"Cake", 2.11),
            )

        }
}