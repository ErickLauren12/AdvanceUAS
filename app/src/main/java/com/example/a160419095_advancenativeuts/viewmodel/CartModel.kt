package com.example.a160419095_advancenativeuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.BookDatabase
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.model.Transaksi
import com.example.a160419095_advancenativeuts.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CartModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val cartLiveData = MutableLiveData<List<Cart>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val cartLoadErrorLiveData = MutableLiveData<Boolean>()

    val TAG = "cartTag"
    //private var rQueue: RequestQueue? = null

    private var job = Job()
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

    fun refresh(){
        cartLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch{
            val db = buildDb(getApplication())
            cartLiveData.value =db.bookDatabase().selectAllCart()
        }
        /*
        rQueue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.19/Advance/cart.json"
        val sType = object : TypeToken<ArrayList<Book>>() {}.type

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                loadingLiveData.value = false
                cartLiveData.value = result
            },
            {
                loadingLiveData.value = false
                cartLoadErrorLiveData.value = true
                Log.d("ErrorVolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }

        rQueue?.add(stringRequest)*/
    }

    fun RestoreStock(id: Int){
        launch {
            val db = buildDb(getApplication())
            val book = db.bookDatabase().selectBook(id)
            val stock = Integer.parseInt(book.stock) + 1
            db.bookDatabase().updateStock(id, stock.toString())
        }
    }

    fun removeCart(cart: Cart){
        launch{
            val db = Room.databaseBuilder(getApplication(), BookDatabase::class.java, "newbookdb").build()
            db.bookDatabase().deleteCart(cart)
            cartLiveData.value = db.bookDatabase().selectAllCart()
        }
    }

    fun checkOut(listTransaksi: List<Transaksi>) {
        launch {
            val db = buildDb(getApplication())
            db.bookDatabase().checkOut(*listTransaksi.toTypedArray())
        }
    }

    override fun onCleared() {
        super.onCleared()
            //rQueue?.cancelAll(TAG)
    }
}