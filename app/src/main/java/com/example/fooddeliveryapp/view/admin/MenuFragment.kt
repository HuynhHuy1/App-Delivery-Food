package com.example.fooddeliveryapp.view.admin

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.CategoryModel
import com.example.fooddeliveryapp.view.admin.adapter.onClickItemMenu
import com.example.fooddeliveryapp.view.customer.adapter.AdapterMenuAdmin
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    var isAdapter = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment_admin = requireActivity().findViewById<FrameLayout>(R.id.fragment_admin)
        fragment_admin.visibility = View.VISIBLE
        requireActivity().findViewById<GridLayout>(R.id.gridLayout_admin).visibility = View.GONE
        val fabAdminMenu = view.findViewById<FloatingActionButton>(R.id.fab_menu_admin)
        setAdapter(view)
        var btnBack = view.findViewById<ImageView>(R.id.btn_back_menu_admin)
        btnBack.setOnClickListener{
            startActivity(Intent(requireContext(),AdminMainActivity::class.java))
        }

        fabAdminMenu.setOnClickListener{
            handOnClickFAB(it)
        }
    }
    fun handOnClickFAB(view: View){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_category, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()

        // handle OK button click
            dialogView.findViewById<Button>(R.id.btn_add_category).setOnClickListener {
                val editText = dialogView.findViewById<EditText>(R.id.ed_add_category)
                val inputText = editText.text.toString()
                ConfigFirebase().addCategoryToFirebasem(CategoryModel(inputText),requireActivity())
                // do something with the input text
                alertDialog.dismiss()
            }

        // handle Cancel button click
            dialogView.findViewById<Button>(R.id.btn_cancle_category).setOnClickListener {
                alertDialog.dismiss()
            }

    }
    fun setAdapter(view: View){
        ConfigFirebase().getCategoryFromFirebase{
            val FoodManageFragment = FoodManageFragment()
                val adapter = AdapterMenuAdmin(it,object : onClickItemMenu{
                    override fun handleOnClickItem(toString: String) {
                        var data = Bundle()
                        data.putString("Category",toString)
                        FoodManageFragment.arguments = data
                        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_admin,FoodManageFragment).commitNow()
                    }
                })
                val rcvMenu = view.findViewById<RecyclerView>(R.id.rcv_menu_admin)
                rcvMenu.adapter = adapter
                rcvMenu.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
                isAdapter = true
        }

    }

}