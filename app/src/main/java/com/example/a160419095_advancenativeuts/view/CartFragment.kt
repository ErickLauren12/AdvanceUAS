package com.example.a160419095_advancenativeuts.view

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.CartListItemBinding
import com.example.a160419095_advancenativeuts.databinding.FragmentCartBinding
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.model.Transaksi
import com.example.a160419095_advancenativeuts.viewmodel.CartModel
import com.example.a160419095_advancenativeuts.viewmodel.ListBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_cart.*
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class CartFragment : Fragment() {

    private  lateinit var  viewModel: CartModel
    private lateinit var dataBinding: FragmentCartBinding
    private  val cartListAdapter = CartAdapter(arrayListOf()) {
        viewModel.RestoreStock(it.bookId)
        viewModel.removeCart(it)
        //viewModel.checkOut(listTransaksi)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container,false)
        return inflater.inflate(R.layout.fragment_cart, container, false)
        //return dataBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(CartModel::class.java)
        viewModel.refresh()

        recyclerViewCart.layoutManager = LinearLayoutManager(context)
        recyclerViewCart.adapter = cartListAdapter

        refreshLayoutCart.setOnRefreshListener {
            txtErrorCart.visibility = View.GONE
            progressLoadCart.visibility = View.GONE

            refreshLayoutCart.isRefreshing = false
            observeViewModel()
        }

        buttonCheckout.setOnClickListener{
            var cartData = viewModel.cartLiveData.value as List<Cart>
            viewModel.deleteAllCart()

            for (item in cartData){
                var dateNow = LocalDate.now()
                val formatTanggal = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                val tanggalSkrg = dateNow.format(formatTanggal)

                var fiveDay = dateNow.plus(Period.of(0,0,7))
                var tanggalAkhirPinjam = fiveDay.format(formatTanggal)

                var transaksi = Transaksi(tanggalSkrg,tanggalAkhirPinjam,item.title,item.image,item.bookId)
                var list = listOf(transaksi)
                viewModel.checkOut(list)
            }
            viewModel.refresh()
        }
//        dataBinding.transaksi = Transaksi(tanggalSkrg,tanggalAkhirPinjam,"","",1)
//        viewModel = ViewModelProvider(this).get(CartModel::class.java)
//        dataBinding.listener = this

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.cartLiveData.observe(viewLifecycleOwner){
            cartListAdapter.updateCartList(it)

            if(it.isEmpty() == true){
                txtErrorCart.visibility = View.VISIBLE
                progressLoadCart.visibility = View.GONE
                //buttonCheckout.visibility = View.GONE
            }else{
                txtErrorCart.visibility = View.GONE
                progressLoadCart.visibility = View.GONE
                //buttonCheckout.visibility = View.VISIBLE
            }
        }

    }

//    override fun onCheckoutClick(v: View, obj: Transaksi) {
//
//    }
}