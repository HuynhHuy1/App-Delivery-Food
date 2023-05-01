package com.example.fooddeliveryapp.view.customer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.view.customer.adapter.FoodStatusAdapter
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
class StatusOrderFragment : Fragment() {
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
        viewModel.orderItem.observe(viewLifecycleOwner) {
            Log.d("Status", "${it.foods.size} ")
            var listFood = it.foods
            var adapterFoodStatus = FoodStatusAdapter(listFood)
            var rcvFoodStatus = view.findViewById<RecyclerView>(R.id.rcv_status_food)
            rcvFoodStatus.adapter = adapterFoodStatus
            rcvFoodStatus.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        }

    }

}