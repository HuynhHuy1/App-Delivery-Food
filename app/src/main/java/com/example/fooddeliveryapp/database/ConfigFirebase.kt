package com.example.fooddeliveryapp.database

import android.app.Activity
import android.net.Uri
import android.provider.ContactsContract.Data
import android.renderscript.Sampler.Value
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import com.example.fooddeliveryapp.model.*
import com.example.fooddeliveryapp.view.customer.`interface`.handleGetData
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
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
                    arrayList.clear()
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
                listCategory.clear()
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
                var newObj = FoodModel(foodModel.name,it!!.toString(),foodModel.category,foodModel.price)
                ref.push().setValue(newObj, object : DatabaseReference.CompletionListener{
                    override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                        Toast.makeText(activity,"Add Food Success",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
    fun addCategoryToFirebasem(category : CategoryModel,activity: Activity){
        val dbRef = dbInstance.reference.child("Category")
        dbRef.push().setValue(category,object : DatabaseReference.CompletionListener{
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                Toast.makeText(activity,"Add Category Success",Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun addOrderToFireBase(order : OrderModel, activity: Activity){
        val dbRef = dbInstance.reference.child("Order/${order.id}")
        dbRef.setValue(order, object : DatabaseReference.CompletionListener{
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                Toast.makeText(activity,"Order Success",Toast.LENGTH_SHORT).show()
            }

        })

    }
    fun getOrderFromFirebase(callback: (ArrayList<OrderModel>) -> Unit){
        var dbRef = dbInstance.getReference("Order")
        var listOrder = ArrayList<OrderModel>()
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listOrder.clear()
                snapshot.children.forEach{
                    val id = it.child("id").getValue(Int::class.java)
                    val address = it.child("address").getValue(String::class.java)
                    val foodsSnapshot = it.child("foods")
                    val foodsList = mutableListOf<FoodModel>()
                    foodsSnapshot.children.forEach { foodSnapshot ->
                        val name = foodSnapshot.child("name").getValue(String::class.java)
                        val image = foodSnapshot.child("image").getValue(String::class.java)
                        val category = foodSnapshot.child("category").getValue(String::class.java)
                        val price = foodSnapshot.child("price").getValue(Double::class.java)
                        val food = FoodModel(name!!, image!!, category!!, price!!)
                        foodsList.add(food)
                    }
                    val userSnapshot = it.child("user")
                    val avatar = userSnapshot.child("avatar").getValue(Int::class.java)
                    val name = userSnapshot.child("name").getValue(String::class.java)
                    val user = User("",name!!.toString(),avatar!!.toString(),address!!, "0935")

                    val total = it.child("total").getValue(Double::class.java)
                    val statusOrder = it.child("statusOrder").getValue(String::class.java)
                    val timeOrder = it.child("timeOrder").getValue(String::class.java)
                    val order = OrderModel(id!!, address!!, foodsList, user!!, total!!, statusOrder!!, timeOrder!!)
                    listOrder.add(order)
                }
                callback(listOrder)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun updateOrder(id : Int, statusOrder1 : String){
        var dbRef = dbInstance.getReference("Order/${id}")
        val updateHashMap = HashMap<String,Any>()
        updateHashMap["statusOrder"] = statusOrder1
        dbRef.updateChildren(updateHashMap)
    }
    fun getAccountFromFireBase(userName : String, accountModel: AccountModel) {
        var dbRef = dbInstance.reference.child("Account")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var listUser = arrayListOf<String>()
                snapshot.children.forEach {
                    var userName = it.child("userName").getValue(String::class.java)
                    if(userName != null){
                        listUser.add(userName)
                    }
                }
                if (listUser.contains(userName)) {
                    // do something when the user already exists
                } else {
                    dbRef.push().setValue(accountModel)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun updateUser(user : User){
        val storageRef = dbStorage.reference.child("image_user").child("avatar/${user.avatar.toUri().lastPathSegment}")
        val uploadTask = storageRef.putFile(user.avatar.toUri())
        uploadTask.onSuccessTask {
            storageRef.downloadUrl.addOnSuccessListener{
                var newUser = User(user.userName,user.name,it.toString(),user.address, user.phoneNumber)
               var dbRef = dbInstance.reference.child("User")
                dbRef.push().setValue(newUser)
            }
        }
    }
    fun getProfileFromFirebae(accountModel: AccountModel,callback: (User) -> Unit){
        val dbRef = dbInstance.reference.child("User")
        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user : User? = null
                snapshot.children.forEach{
                    if(accountModel.userName == it.child("userName").getValue(String::class.java)){
                        var userName  = it.child("userName").getValue(String::class.java)
                        var address  = it.child("address").getValue(String::class.java)
                        var avater  = it.child("avatar").getValue(String::class.java)
                        var phone  = it.child("phoneNumber").getValue(String::class.java)
                        var name = it.child("name").getValue(String::class.java)
                        user = User(userName!!,name!!,avater!!,address!!,phone!!)
                    }
                }
                if(user != null){
                    callback(user!!)
                }
                else{
                    callback(User("","","","",""))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}
