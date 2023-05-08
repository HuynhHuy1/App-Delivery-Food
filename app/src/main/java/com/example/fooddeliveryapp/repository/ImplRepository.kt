package com.example.fooddeliveryapp.repository

import android.graphics.ColorSpace
import android.util.Log
import com.example.fooddeliveryapp.model.FoodModel
import com.google.firebase.database.*
import kotlin.coroutines.suspendCoroutine

class ImplRepository : MyRepository {
    val databaseRefFood : DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("Food")
    }
    var datalistFoodModel : List<FoodModel>? = null

    private suspend fun getDataFromFirebase(): List<FoodModel> {
        var listFoodTemp = ArrayList<FoodModel>()
        return suspendCoroutine { continuation ->
            databaseRefFood.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(foodSnapShot in snapshot.children){
                        var foodModelName = foodSnapShot.child("name").getValue(String::class.java)
                        var foodModelImage = foodSnapShot.child("image").getValue(String::class.java)
                        var foodModelCategory  =foodSnapShot.child("category").getValue(String::class.java)
                        var price  = foodSnapShot.child("price").getValue(Double::class.java)
                        if (foodModelName != null && foodModelCategory != null && price != null && foodModelImage != null){
                            listFoodTemp.add(FoodModel(foodModelName,foodModelImage,foodModelCategory,price))
                            Log.d("TAG", "get data : $foodModelImage")
                        }
                        else{
                            Log.d("TAG", "onDataChange: ")
                        }
                    }
                    datalistFoodModel = listFoodTemp.toList()
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }

    override suspend fun getModelFoodList(): List<FoodModel> {
            if (datalistFoodModel != null) {
                return datalistFoodModel!!
            }
            return getDataFromFirebase()
        }

    }