package com.example.a160419095_advancenativeuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.BookListItemBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList:ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(), BookListDetailClickListener, ButtonEditBookClickListener {
    class BookViewHolder(var view:BookListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = BookListItemBinding.inflate(inflater, parent, false)
        return  BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        with(holder.view){
            book = bookList[position]
            detailListener = this@BookListAdapter
            editListener = this@BookListAdapter
        }
        /*
        val book = bookList[position]
        with(holder.view){
            txtTitle.text = book.title
            txtDescription.text = book.description
            imageBook.loadImage(book.photoUrl, progressLoadingPhoto)

            buttonDetail.setOnClickListener {
                val action = BookListFragmentDirections.actionBookDetail(book.bookId.toString())
                Navigation.findNavController(it).navigate(action)
            }

        }*/
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onBookDetailClick(view: View) {
        val bookId = view.tag.toString()
        val action = BookListFragmentDirections.actionBookDetail(bookId)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onBookEditClick(view: View) {
        val bookId = view.tag.toString()
        val action = BookListFragmentDirections.actionEditBook(bookId)
        Navigation.findNavController(view).navigate(action)
    }
}