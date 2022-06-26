package com.example.a160419095_advancenativeuts.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.FragmentBookDetailBinding
import com.example.a160419095_advancenativeuts.databinding.FragmentAddBookBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.viewmodel.BookDetailModel
import kotlinx.android.synthetic.main.fragment_add_book.*

class AddBookFragment : Fragment(), ButtonAddTodoClickListener {
    private lateinit var viewModel: BookDetailModel
    private lateinit var dataBinding: FragmentAddBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //dataBinding = FragmentAddBookBinding.inflate(inflater, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_book,
                      container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //instance
        dataBinding.book = Book("","","","",0,"")
        viewModel = ViewModelProvider(this).get(BookDetailModel::class.java)
        dataBinding.listener = this

    }

    override fun onButtonAddTodo(v: View) {
        val list = listOf(dataBinding.book!!)
        viewModel.addBook(list)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setMessage("Book Data successfully added")
            setNegativeButton("BACK"){ _, _ ->
                Navigation.findNavController(v).popBackStack()
            }
            create().show()
        }
    }

//    fun observeViewModel() {
//        viewModel.
//    }



}