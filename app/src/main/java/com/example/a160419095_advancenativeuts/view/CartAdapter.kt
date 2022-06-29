package com.example.a160419095_advancenativeuts.view

import android.app.AlertDialog
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.CartListItemBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.model.Transaksi
import com.example.a160419095_advancenativeuts.util.loadImage
import com.example.a160419095_advancenativeuts.viewmodel.CartModel
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CartAdapter(val cartList:ArrayList<Cart>, val adapterOnClick: (Cart)->Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(), CartRemoveClickListener{
    class CartViewHolder(var view:CartListItemBinding) : RecyclerView.ViewHolder(view.root)
    private  lateinit var viewModel : CartModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CartListItemBinding.inflate(inflater, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.view.bookCart = cartList[position]
        holder.view.removeListener = this
        /*with(holder.view){
            bookCart = cartList[position]
        }*/
    }

    override fun getItemCount() = cartList.size

    fun updateCartList(newCartList: List<Cart>){
        cartList.clear()
        cartList.addAll(newCartList)
        notifyDataSetChanged()
    }

    override fun onCartRemoveClick(view: View, obj: Cart) = adapterOnClick(obj)

}