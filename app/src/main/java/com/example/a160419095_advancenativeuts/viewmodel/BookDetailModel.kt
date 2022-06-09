package com.example.a160419095_advancenativeuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookDetailModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    val bookLiveData = MutableLiveData<Book>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val bookLoadErrorLiveData = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /*

    val TAG = "DetailBookTag"*/
    private var rQueue: RequestQueue? = null

    fun detail(id: Int){

        launch {
            val db = buildDb(getApplication())
            bookLiveData.value = db.bookDatabase().selectBook(id)
            bookLoadErrorLiveData.value = false
            loadingLiveData.value = true

            /*
            rQueue = Volley.newRequestQueue(getApplication())
            val url = "http://192.168.18.19/Advance/$id.json"
            val sType = object : TypeToken<Book>(){}.type
            Log.d("Result Detail", id)
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                {
                    val result = Gson().fromJson<Book>(it, sType)

                    loadingLiveData.value = false
                    bookLiveData.value = result
                    Log.d("ShowVolley", it)
                },
                {
                    loadingLiveData.value = false
                    bookLoadErrorLiveData.value = true
                    Log.d("ErrorVolley", it.toString())
                }
            ).apply {
                tag = "TAG"
            }
            rQueue?.add(stringRequest)*/
        }

    }

    override fun onCleared() {
        super.onCleared()
        //rQueue?.cancelAll(TAG)
    }
}