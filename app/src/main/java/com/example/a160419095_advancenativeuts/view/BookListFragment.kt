package com.example.a160419095_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.viewmodel.ListBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment() {
    private  val bookListAdapter = BookListAdapter(arrayListOf())
    private  lateinit var  viewModel: ListBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListBookViewModel::class.java)
        viewModel.refresh()

        recyclerViewHistory.layoutManager = LinearLayoutManager(context)
        recyclerViewHistory.adapter = bookListAdapter

        floatingAdd.setOnClickListener {
            val action = BookListFragmentDirections.actionAddBook()
            Navigation.findNavController(it).navigate(action)
        }

        refreshLayout.setOnRefreshListener {
            textError.visibility = View.GONE
            progressLoad.visibility
            refreshLayout.isRefreshing = false
            observeViewModel()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)

            if(it.isEmpty() == true){
                textEmpty.visibility = View.VISIBLE
                textError.visibility = View.GONE
                progressLoad.visibility = View.GONE
            }else{
                textEmpty.visibility = View.GONE
                textError.visibility = View.GONE
                progressLoad.visibility = View.GONE
            }
        }

        viewModel.bookLoadErrorLiveData.observe(viewLifecycleOwner){
            textError.visibility = if(it) View.VISIBLE else View.GONE
        }
    }
}