package com.example.a160419095_advancenativeuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.CartListItemBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.util.loadImage
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

class CartAdapter(val cartList:ArrayList<Cart>, val adapterOnClick: (Cart)->Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(), CartRemoveClickListener{
    class CartViewHolder(var view:CartListItemBinding) : RecyclerView.ViewHolder(view.root)

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

    override fun onCartRemoveClick(view: View, obj: Cart) {
        adapterOnClick(obj)
    }
}