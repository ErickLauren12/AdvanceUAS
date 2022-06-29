package com.example.a160419095_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.viewmodel.CartModel
import com.example.a160419095_advancenativeuts.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.progressLoad
import kotlinx.android.synthetic.main.fragment_history.recyclerViewHistory
import kotlinx.android.synthetic.main.fragment_history.textError


class HistoryFragment : Fragment() {
    private var historyAdapter = HistoryAdapter(arrayListOf())
    private  lateinit var  viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        viewModel.historyTransaksi()

        recyclerViewHistory.layoutManager = LinearLayoutManager(context)
        recyclerViewHistory.adapter = historyAdapter

        refreshLayoutHistory.setOnRefreshListener {
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            refreshLayoutHistory.isRefreshing = false
            observeViewModel()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.transaksiLiveData.observe(viewLifecycleOwner) {
            historyAdapter.updateHistoryList(it)

            if(it.isEmpty() == true){
                historyTxtEmpty.visibility = View.VISIBLE
                textError.visibility = View.GONE
                progressLoad.visibility = View.GONE
            }else{
                historyTxtEmpty.visibility = View.GONE
                textError.visibility = View.GONE
                progressLoad.visibility = View.GONE
            }
        }

        viewModel.transaksiLoadErrorLiveData.observe(viewLifecycleOwner){
            textError.visibility = if(it) View.VISIBLE else View.GONE
        }
    }
}