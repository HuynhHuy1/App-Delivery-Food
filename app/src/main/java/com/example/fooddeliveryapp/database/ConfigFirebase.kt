package com.example.fooddeliveryapp.database

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.model.FoodModel
import com.example.fooddeliveryapp.view.customer.`interface`.handleGetData
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import javax.security.auth.callback.Callback

class ConfigFirebase() {
     val dbInstance :  FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    val arrayList : ArrayList<FoodModel> by lazy{
        ArrayList<FoodModel>()
    }
    val dbStorage : FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
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
    fun getCategoryFromFirebase(callback: (ArrayList<CategoryModel>) -> Unit){
        var dbRefCategory = dbInstance.getReference("Category")
        var listCategory = ArrayList<CategoryModel>()
        dbRefCategory.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(categorySnapShot in snapshot.children){
                    var categoryName = categorySnapShot.child("name").getValue(String::class.java)
                    listCategory.add(CategoryModel(categoryName.toString()))
                }
                callback(listCategory)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Lỗi get data")
            }
        }
        )
    }
    fun addFoodToFirebase(foodModel: FoodModel, activity: Activity){
        val storageRef = dbStorage.reference.child("image_food").child("Food/${foodModel.image.toUri().lastPathSegment}")
        val uploadTask = storageRef.putFile(foodModel.image.toUri())
        uploadTask.onSuccessTask {
            storageRef.downloadUrl.addOnSuccessListener {
               var ref =  dbInstance.reference.child("Food")
                ref.push().setValue(foodModel, object : DatabaseReference.CompletionListener{
                    override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                        Toast.makeText(activity,"Add Food Success",Toast.LENGTH_SHORT)
                    }
                })
            }
        }
    }

}
