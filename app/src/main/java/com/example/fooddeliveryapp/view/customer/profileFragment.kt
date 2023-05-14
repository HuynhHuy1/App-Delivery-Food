package com.example.fooddeliveryapp.view.customer

import android.content.Intent
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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.AccountModel
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class profileFragment(var newAccount: AccountModel,var user: User) : Fragment() {
    private val viewModel : SendDataViewModel by lazy{
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }
    var  uri : Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var bottomnav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView_order)
        bottomnav.visibility = View.GONE
        checkInputInfo(view)
        setLayoutProfile(view)
        var btnBack = view.findViewById<ImageView>(R.id.btn_back_profile)
        btnBack.setOnClickListener{
            fragmentManager?.popBackStack()
        }
        var FAB = requireActivity().findViewById<FloatingActionButton>(R.id.FAB_profile)
        FAB.setOnClickListener {
            pickerImage()
        }
    }
    private fun checkInputInfo(view: View){

        val imageInfo = view.findViewById<ImageView>(R.id.image_info)
        val btnUpdateInfo = view.findViewById<Button>(R.id.btn_updateInfo)
        btnUpdateInfo.setOnClickListener{
            val tv1 = view.findViewById<EditText>(R.id.profile_ed1).text.toString()
            val tv3 = view.findViewById<EditText>(R.id.profile_ed3).text.toString()
            val tv4 = view.findViewById<EditText>(R.id.profile_ed4).text.toString()
            var newUser = User(newAccount.userName,tv1,uri.toString(),tv3,tv4)
            val homeFragment = HomeFragment(newAccount,newUser)
            ConfigFirebase().getAccountFromFireBase(newAccount.userName,newAccount )
            Toast.makeText(requireActivity(),"${newAccount.userName}",Toast.LENGTH_SHORT).show()
            ConfigFirebase().updateUser(newUser,newAccount.userName)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment,homeFragment).commitNow()
        }
    }
    private fun pickerImage(){
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,1234)
        Log.d("TAG", "pickerImage: ")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            uri = data?.data
            val image = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,uri)
            val imageView = requireActivity().findViewById<ImageView>(R.id.imageview_profile_fragment)
            imageView?.setImageBitmap(image)
    }
    fun setLayoutProfile(view : View){
        view.findViewById<EditText>(R.id.profile_ed1).hint = user.name
        view.findViewById<EditText>(R.id.profile_ed3).hint = user.address
        view.findViewById<EditText>(R.id.profile_ed4).hint = user.phoneNumber
        if(user.userName == ""){
            view.findViewById<ImageView>(R.id.imageview_profile_fragment).setImageResource(R.drawable.baseline_person_24)
        }
        else{
            var imageView = view.findViewById<ImageView>(R.id.imageview_profile_fragment)
            Picasso.get().load(user.avatar).into(imageView)
        }
    }

}