package com.example.a160419095_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.util.loadImage
import com.example.a160419095_advancenativeuts.viewmodel.BookDetailModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: BookDetailModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BookDetailModel::class.java)
        val id = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId
        viewModel.detail(id.toInt())

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            imageBookDetail.loadImage(it.photoUrl,progressBookDetailLoad)
            textTitleDetail.text = it.title
            textWriterDetail.text = it.writer
            textReleaseDate.text = it.releaseDate
            textDescriptionDetail.text = it.description
        }
    }
}