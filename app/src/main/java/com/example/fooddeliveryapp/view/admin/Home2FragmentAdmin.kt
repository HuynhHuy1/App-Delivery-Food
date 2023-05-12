package com.example.fooddeliveryapp.view.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.OrderModel
import com.example.fooddeliveryapp.view.admin.adapter.onClickOrder
import com.example.fooddeliveryapp.view.customer.adapter.OrderAdminAdapter

class Home2FragmentAdmin : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment_admin2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter(view)
    }
//    fun handleOnClickCompleteBtn(view : View){
//        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_admin,home2).commitNow()
//    }
    fun setAdapter(view : View){
        ConfigFirebase().getOrderFromFirebase {
            var listInProgress = ArrayList<OrderModel>()
            it.forEach{
                if(it.statusOrder == "Complete" ){
                    listInProgress.add(it)
                }
            }
            var adapterOrderAdmin = OrderAdminAdapter(listInProgress, object : onClickOrder{
                override fun handleOnClickConfirm(position: Int, id: Int) {
                }

                override fun handleOnClickComplete(position: Int, id: Int) {
//                    handleOnClickCompleteBtn(view)
                }

            })
            Log.d("TAG", "onViewCreated: frg2")
            adapterOrderAdmin.notifyDataSetChanged()
            var rcvorderAdmin = requireActivity().findViewById<RecyclerView>(R.id.rcv_home_fragment_admin_2)
            rcvorderAdmin.adapter = adapterOrderAdmin
            rcvorderAdmin.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)

        }
    }
}