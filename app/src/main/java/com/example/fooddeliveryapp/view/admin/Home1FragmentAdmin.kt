package com.example.fooddeliveryapp.view.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.OrderModel
import com.example.fooddeliveryapp.view.admin.adapter.onClickOrder
import com.example.fooddeliveryapp.view.customer.adapter.OrderAdminAdapter
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

class Home1FragmentAdmin : Fragment() {
    val viewModel : SendDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }
    private val completeFragmentAdmin : MenuFragment by lazy {
        MenuFragment()
    }

    val home2 : Home2FragmentAdmin by lazy {
        Home2FragmentAdmin()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment_admin_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter(view)
    }

    private fun handleCLickConfirm(view: View, id: Int) {
        val confirmBtn = view.findViewById<TextView>(R.id.btn_admin_confirm)
        val compleTeBtn = view.findViewById<TextView>(R.id.btn_complete_admin)
        confirmBtn.visibility = View.GONE
        compleTeBtn.visibility = View.VISIBLE
        ConfigFirebase().updateOrder(id,"Confirm")
        Log.d("TAG", "handleOnClickCompleteBtn: ")
    }

    fun handleOnClickCompleteBtn(view : View,id: Int){
        ConfigFirebase().updateOrder(id,"Complete")
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_admin,home2).commitNow()
    }
    fun setAdapter(view : View){
        ConfigFirebase().getOrderFromFirebase {
            var listInProgress = ArrayList<OrderModel>()
            it.forEach{
                if(it.statusOrder == "Confirm"){
                    listInProgress.add(it)
                }
            }
            var adapterOrderAdmin = OrderAdminAdapter(listInProgress, object : onClickOrder{
                override fun handleOnClickConfirm(position: Int, id: Int) {
                    handleCLickConfirm(view,id)
                }

                override fun handleOnClickComplete(position : Int,id: Int) {
                    handleOnClickCompleteBtn(view,id)
                }

            })
            adapterOrderAdmin.notifyDataSetChanged()
            var rcvorderAdmin = view.findViewById<RecyclerView>(R.id.rcv_status_order_admin)
            rcvorderAdmin.adapter = adapterOrderAdmin
            rcvorderAdmin.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)

        }
    }
}