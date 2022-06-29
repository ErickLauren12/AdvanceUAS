package com.example.a160419095_advancenativeuts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.FragmentAddBookBinding
import com.example.a160419095_advancenativeuts.databinding.FragmentEditBookBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.viewmodel.BookDetailModel

class EditBook : Fragment(), ButtonUpdateBookClickListener {
    private lateinit var viewModel: BookDetailModel
    private lateinit var dataBinding: FragmentEditBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_book,
            container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.book = Book("","","","","","")
        dataBinding.updateListener = this

        viewModel = ViewModelProvider(this).get(BookDetailModel::class.java)
        val id = EditBookArgs.fromBundle(requireArguments()).bookId
        viewModel.detail(id.toInt())
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            dataBinding.book = it
        }
    }

    override fun onBookUpdateClick(view: View, obj: Book) {
        Log.d("Data Book", obj.toString())
        viewModel.update(obj)
        Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
}