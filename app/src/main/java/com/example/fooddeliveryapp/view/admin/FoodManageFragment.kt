package com.example.fooddeliveryapp.view.admin

import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
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
    var dialogView : View? = null
    private var selectedImageView: ImageView? = null
    private var cardImageView: CardView? = null
    private var btnImageView: Button? = null
    private var uri : Uri? = null
    private var isAdapterSet = false
    lateinit var foodManageAdapter : FoodManageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tvTitleCategory : TextView

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.food_manage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_food, null)
        selectedImageView = dialogView?.findViewById<ImageView>(R.id.imageView_add_food)
        btnImageView= dialogView?.findViewById<Button>(R.id.add_image_food)
        cardImageView = dialogView?.findViewById<CardView>(R.id.cardView_add_food)
        val FAB = view.findViewById<FloatingActionButton>(R.id.fab_food_admin)
        FAB.setOnClickListener{
            handleOnClickFAB(dialogView!!)
        }
        getFood(view)

    }
    fun handleOnClickFAB(dialogView : View){
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        val btn_add_image = dialogView.findViewById<Button>(R.id.add_image_food)
        btn_add_image?.setOnClickListener{
            openGallery()
        }
        // handle OK button click
        dialogView.findViewById<Button>(R.id.btn_add_food).setOnClickListener {
            val editText1 = dialogView.findViewById<EditText>(R.id.ed_name_add_food).text.toString()
            val editText2 = dialogView.findViewById<EditText>(R.id.ed_price_add_food).text.toString().toDouble()
            val editText3 =  view?.findViewById<TextView>(R.id.tile_food_manage)?.text.toString()
            var foodModel = FoodModel(editText1,uri.toString(),editText3,editText2)
            Log.d("TAG", "handleOnClickFAB: ${editText3.toString()}")
            alertDialog.dismiss()
            ConfigFirebase().addFoodToFirebase(foodModel,requireActivity())
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
                isAdapterSet = true
                foodManageAdapter = FoodManageAdapter(newList,requireActivity())

                val rcvFood = view.findViewById<RecyclerView>(R.id.rcv_food_admin)
                rcvFood.adapter = foodManageAdapter
                rcvFood.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)

    }
    fun getFood(view: View){

        ConfigFirebase().firebaseReferenceFood {
            Log.d("TAG", "getFood: ${it.size}")
            setUpAdapter(view,it)
        }
    }
    fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,1001)
        Log.d("TAG", "openGallery: ")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        uri = data?.data
        val image = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,uri)
        btnImageView?.visibility = View.GONE
        cardImageView?.visibility = View.VISIBLE
        selectedImageView?.setImageBitmap(image)
    }
}