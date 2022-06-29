package com.example.a160419095_advancenativeuts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419095_advancenativeuts.databinding.CartListItemBinding
import com.example.a160419095_advancenativeuts.databinding.HistoryListItemBinding
import com.example.a160419095_advancenativeuts.model.Transaksi

class HistoryAdapter(val listTransaksi: ArrayList<Transaksi>) : RecyclerView.Adapter<
        HistoryAdapter.HistoryViewHolder>(){
    class HistoryViewHolder(var view: HistoryListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HistoryListItemBinding.inflate(inflater, parent, false)
        return HistoryAdapter.HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listTransaksi.size
}