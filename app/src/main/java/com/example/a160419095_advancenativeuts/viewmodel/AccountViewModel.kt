package com.example.a160419095_advancenativeuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160419095_advancenativeuts.model.Account
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.util.buildDb
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AccountViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
//    val accountLiveData = MutableLiveData<List<Account>>()
//    val loadingLiveData = MutableLiveData<Boolean>()
//    val cartLoadErrorLiveData = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun register(list: List<Account>) {
        launch {
            val db = buildDb(getApplication())
            db.bookDatabase().insertAllAccount(*list.toTypedArray())

        }
    }

    fun login(username:String, pass:String) {
        launch {
            val db = buildDb(getApplication())
            db.bookDatabase().loginAccount(username,pass)
        }
    }
}