package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

class profileFragment : Fragment() {
    private val viewModel : SendDataViewModel by lazy{
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
    private fun checkInputInfo(view: View){
        val tv1 = view.findViewById<EditText>(R.id.profile_ed1).toString()
        val tv2 = view.findViewById<EditText>(R.id.profile_ed2).toString()
        val tv3 = view.findViewById<EditText>(R.id.profile_ed3).toString()
        val tv4 = view.findViewById<EditText>(R.id.profile_ed4).toString()
        val imageInfo = view.findViewById<ImageView>(R.id.image_info)
        val btnUpdateInfo = view.findViewById<Button>(R.id.btn_updateInfo)
        btnUpdateInfo.setOnClickListener{
            if(tv1 == "" || tv2 == "" || tv3 == "" || tv4 == "" ){
                Toast.makeText(view.context,"Please Enter Full Fiel",Toast.LENGTH_SHORT)
            }
            else{
                Toast.makeText(view.context,"Update Success",Toast.LENGTH_SHORT).show()

            }
        }
    }

}