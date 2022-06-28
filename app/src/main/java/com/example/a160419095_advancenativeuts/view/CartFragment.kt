package com.example.a160419095_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.viewmodel.CartModel
import com.example.a160419095_advancenativeuts.viewmodel.ListBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {
    private  lateinit var  viewModel: CartModel
    private  val cartListAdapter = CartAdapter(arrayListOf()) {
        viewModel.RestoreStock(it.bookId)
        viewModel.removeCart(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(CartModel::class.java)
        viewModel.refresh()

        recyclerViewCart.layoutManager = LinearLayoutManager(context)
        recyclerViewCart.adapter = cartListAdapter

        refreshLayoutCart.setOnRefreshListener {
            recyclerViewCart.visibility = View.GONE
            txtErrorCart.visibility = View.GONE
            progressLoadCart.visibility = View.GONE

            refreshLayoutCart.isRefreshing = false
            observeViewModel()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.cartLiveData.observe(viewLifecycleOwner){
            cartListAdapter.updateCartList(it)

            if(it.isEmpty() == true){
                recyclerViewCart.visibility = View.GONE
                txtErrorCart.visibility = View.VISIBLE
                progressLoadCart.visibility = View.GONE
            }else{
                recyclerViewCart.visibility = View.VISIBLE
                txtErrorCart.visibility = View.GONE
                progressLoadCart.visibility = View.GONE
            }
        }

    }
}