package com.example.a160419095_advancenativeuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160419095_advancenativeuts.model.Transaksi
import com.example.a160419095_advancenativeuts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    val transaksiLiveData = MutableLiveData<List<Transaksi>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val transaksiLoadErrorLiveData = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main



    fun historyTransaksi() {
        loadingLiveData.value = false
        transaksiLoadErrorLiveData.value = false
        launch {
            val db = buildDb(getApplication())
            transaksiLiveData.value = db.bookDatabase().historyTransaction()
        }
    }

}