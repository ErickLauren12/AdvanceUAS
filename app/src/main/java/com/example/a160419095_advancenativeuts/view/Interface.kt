package com.example.a160419095_advancenativeuts.view

import android.view.View
import com.example.a160419095_advancenativeuts.model.Cart

interface BookListDetailClickListener{
    fun onBookDetailClick(view: View)
}

interface CartRemoveClickListener{
    fun onCartRemoveClick(view: View, obj:Cart)
}
