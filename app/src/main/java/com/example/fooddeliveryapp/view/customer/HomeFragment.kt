package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        setCategoryAdapter(view)

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
//       private fun setAdapterFood(view : View){
//           var configFirebase = ConfigFirebase()
//           configFirebase.firebaseReferenceFood{
//               var list = it.toList()
//               var listTea = CategoryModel("Tea")
//               var listCoffe =  CategoryModel("Coffee")
//               var listFreeze = CategoryModel("Ice Blended")
//               var listCake =  CategoryModel("Cake")
//               var listAddItem = arrayListOf<FoodModel>()
//               var ListcategoryModel = listOf<CategoryModel>(
//                   listCoffe,
//                   listTea,listFreeze,listCake
//               )
//               var adapterCategory = CategoryAdapter(ListcategoryModel,object : handleOnClick {
//                   override fun onClickItem(toString: String) {
//                       clickItemCategory(ListcategoryModel,toString,view,object : handleFoodItem {
//                           override fun clickAddFood(view: View, foodModel: FoodModel) {
//                               handleAddItemHome(view,listAddItem,foodModel)
//                               Log.d("List Add Item", "${1} : ${listAddItem[0].name}" )
//                           }
//                       })
//
//                   }
//               })
//               var adapterFood = FoodAdapter(list,object : handleFoodItem {
//                   override fun clickAddFood(view: View, foodModel: FoodModel) {

//                   }
//
//               })
//               var rcvFood = view.findViewById<RecyclerView>(R.id.rcvFood)
//               rcvFood.adapter = adapterFood
//               rcvFood.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
//               var rcvCategory = view.findViewById<RecyclerView>(R.id.linear_list_cate1)
//               rcvCategory.adapter = adapterCategory
//               rcvCategory.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
//           }
//       }
    private fun setCategoryAdapter(view: View){
        ConfigFirebase().getCategoryFromFirebase {
            var rcvFood = requireActivity().findViewById<RecyclerView>(R.id.rcvFood)
            var adapterCategory = CategoryAdapter(it,object : handleOnClick {
                override fun onClickItem(toString: String) {
                }
            },rcvFood,viewModel)
            var rcvCategory = view.findViewById<RecyclerView>(R.id.linear_list_cate1)
            rcvCategory.adapter = adapterCategory
            rcvCategory.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        }
    }
}