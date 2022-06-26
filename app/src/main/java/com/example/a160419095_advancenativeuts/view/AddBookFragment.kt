package com.example.a160419095_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.FragmentBookDetailBinding
import com.example.a160419095_advancenativeuts.databinding.FragmentAddBookBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.viewmodel.BookDetailModel
import kotlinx.android.synthetic.main.fragment_add_book.*

class AddBookFragment : Fragment() {
    private lateinit var viewModel: BookDetailModel
    private lateinit var dataBinding: FragmentAddBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentAddBookBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BookDetailModel::class.java)

        //instance
        dataBinding.book = Book("","","","",0,"")
    }

}