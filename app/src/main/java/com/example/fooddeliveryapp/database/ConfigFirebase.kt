package com.example.fooddeliveryapp.database

import android.util.Log
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.`interface`.handleGetData
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import javax.security.auth.callback.Callback

class ConfigFirebase() {
     val dbInstance :  FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    val arrayList : ArrayList<FoodModel> by lazy{
        ArrayList<FoodModel>()
    }
        fun firebaseReferenceFood(callback: (ArrayList<FoodModel>) -> Unit){
            var dbRefFood = dbInstance.getReference("Food")
            dbRefFood.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(foodSnapShot in snapshot.children){
                        var foodModelName = foodSnapShot.child("name").getValue(String::class.java)
                        var foodModelImage = foodSnapShot.child("image").getValue(String::class.java)
                        var foodModelCategory  =foodSnapShot.child("category").getValue(String::class.java)
                        var price  = foodSnapShot.child("price").getValue(Double::class.java)
                        if (foodModelName != null && foodModelCategory != null && price != null && foodModelImage != null){
                            arrayList.add(FoodModel(foodModelName,foodModelImage,foodModelCategory,price))
                            Log.d("TAG", "get data : $foodModelImage")
                        }
                        else{
                            Log.d("TAG", "onDataChange: ")
                        }
                    }
                    callback(arrayList)

                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "Lỗi get data")
                }

            }
            )
        }

}
