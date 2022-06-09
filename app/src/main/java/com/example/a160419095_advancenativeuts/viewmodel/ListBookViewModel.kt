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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListBookViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val bookLiveData = MutableLiveData<List<Book>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val bookLoadErrorLiveData = MutableLiveData<Boolean>()

    //val TAG = "listBookTag"
    //private var rQueue: RequestQueue? = null

    private var job = Job()
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

    fun refresh(){
        //rQueue = Volley.newRequestQueue(getApplication())

        loadingLiveData.value = true
        bookLoadErrorLiveData.value = false

        launch {
            val db = Room.databaseBuilder(getApplication(), BookDatabase::class.java,
            "newbookdb").build()

            bookLiveData.value = db.bookDao().selectAllBook()
        }
        /*
        val url = "http://192.168.18.19/Advance/book.json"
        val sType = object : TypeToken<ArrayList<Book>>() {}.type

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                loadingLiveData.value = false
                bookLiveData.value = result
            },
            {
                bookLoadErrorLiveData.value = true
                loadingLiveData.value = false
            }
        ).apply {
            tag = "TAG"
        }

        rQueue?.add(stringRequest)*/
    }

    fun clearTask(book: Book){
        launch {
            val db = Room.databaseBuilder(getApplication(), BookDatabase::class.java,
                "newbookdb").build()
            db.bookDao().deleteBook(book)
            bookLiveData.value =db.bookDao().selectAllBook()
        }
    }
    /*
    override fun onCleared() {
        super.onCleared()
        rQueue?.cancelAll(TAG)
    }*/
}