package com.example.a160419095_advancenativeuts.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.FragmentBookDetailBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.Cart
import com.example.a160419095_advancenativeuts.util.loadImage
import com.example.a160419095_advancenativeuts.viewmodel.BookDetailModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment(), AddCartClickListener {
    private lateinit var viewModel: BookDetailModel
    private lateinit var dataBinding:FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BookDetailModel::class.java)
        val id = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId
        viewModel.detail(id.toInt())

        dataBinding.addListener = this
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            dataBinding.book = it
            /*
            imageBookDetail.loadImage(it.photoUrl,progressBookDetailLoad)
            textTitleDetail.text = it.title
            textWriterDetail.text = it.writer
            textReleaseDate.text = it.releaseDate
            textDescriptionDetail.text = it.description*/
        }
    }

    override fun onAddCartClick(view: View, obj: Book) {

        var bookStock = Integer.parseInt(obj.stock)
        val builder = AlertDialog.Builder(context)
        if(bookStock  > 0){
            bookStock -= 1

            viewModel.updateStock(obj.bookId, bookStock.toString())

            var cart = Cart(obj.bookId, obj.title, obj.photoUrl)
            var list = listOf(cart)

            viewModel.addCart(list)

            with(builder) {
                setMessage("Book Added To Cart")
                setNegativeButton("BACK"){ _, _ ->
                    Navigation.findNavController(view).popBackStack()
                }
                create().show()
            }
        }else{

            with(builder) {
                setMessage("Book Stock Is Empty")
                setNegativeButton("BACK"){ _, _ ->
                    Navigation.findNavController(view).popBackStack()
                }
                create().show()
            }
        }
    }
/*
    override fun onButtonAddBook(v: View, obj: Book) {
        var cart = Cart(obj.title, obj.photoUrl)
        var list = listOf(cart)
        Log.d("List cart",list.toString())
        viewModel.addCart(list)
    }*/
}