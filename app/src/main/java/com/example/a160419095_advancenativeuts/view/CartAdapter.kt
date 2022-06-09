package com.example.a160419095_advancenativeuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.util.loadImage
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

class CartAdapter(val cartList:ArrayList<Book>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    class CartViewHolder(var view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_list_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = cartList[position]
        with(holder.view){
            txtCartTitle.text = cart.title
            imageCart.loadImage(cart.photoUrl, progressBarLoadImageCart)
        }
    }

    override fun getItemCount() = cartList.size

    fun updateCartList(newCartList: ArrayList<Book>){
        cartList.clear()
        cartList.addAll(newCartList)
        notifyDataSetChanged()
    }
}