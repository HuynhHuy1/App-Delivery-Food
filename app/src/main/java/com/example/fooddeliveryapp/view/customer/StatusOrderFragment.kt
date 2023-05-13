package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.customer.adapter.OrderStatusAdapter
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatusOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatusOrderFragment(user: User) : Fragment() {
    val viewModel : SendDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ConfigFirebase().getOrderFromFirebase {

            var adapterStatusOrder = OrderStatusAdapter(it)
            var rcvStatus = view.findViewById<RecyclerView>(R.id.rcv_status)
            rcvStatus.adapter = adapterStatusOrder
            rcvStatus.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        }


    }

}