package com.example.a160419095_advancenativeuts.view

import android.view.View
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.model.Transaksi

interface BookListDetailClickListener{
    fun onBookDetailClick(view: View)
}

interface AddCartClickListener{
    fun onAddCartClick(view: View, obj:Book)
}

interface CartRemoveClickListener{
    fun onCartRemoveClick(view: View, obj:Cart)
}

interface ButtonAddBookClickListener {
    fun onButtonAddBook(v:View, obj: Book)
}

//interface ButtonLoginClickListener {
//    fun onButtonLogin(v:View, obj: Account)
//}
//
//interface ButtonRegisterClickListener {
//    fun onButtonRegister(v:View, obj: Account)
//}

interface CheckoutClickListener {
    fun onCheckoutClick(v:View, obj:Transaksi)
}

