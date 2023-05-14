import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.database.ConfigFirebase
import com.example.fooddeliveryapp.model.User
import com.example.fooddeliveryapp.view.customer.adapter.OrderStatusAdapter
import com.example.fooddeliveryapp.viewmodel.SendDataViewModel

class HistoryOrderrFragment(var user: User) : Fragment() {
    val viewModel : SendDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_orderr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBack = view.findViewById<ImageView>(R.id.btn_back_order_status_history)
        btnBack.setOnClickListener{
            fragmentManager?.popBackStack()
        }

        ConfigFirebase().getOrderFromFirebase {
            val listOrder = it.filter{ it.user.userName == user.userName}
            val lisrOrderNotComplete = listOrder.filter { it.statusOrder == "Complete" }
            var adapterStatusOrder = OrderStatusAdapter(lisrOrderNotComplete)
            var rcvStatus = view.findViewById<RecyclerView>(R.id.rcv_status_history)
            rcvStatus.adapter = adapterStatusOrder
            rcvStatus.layoutManager = LinearLayoutManager(view.context,
                LinearLayoutManager.VERTICAL,false)
        }
    }

}