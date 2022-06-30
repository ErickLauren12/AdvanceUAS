package com.example.a160419095_advancenativeuts.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.databinding.FragmentAddBookBinding
import com.example.a160419095_advancenativeuts.databinding.FragmentEditBookBinding
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.viewmodel.BookDetailModel
import kotlinx.android.synthetic.main.fragment_add_book.view.*
import kotlinx.android.synthetic.main.fragment_edit_book.view.*
import java.util.*

class EditBook : Fragment(),
    ButtonUpdateBookClickListener,
    DateClickListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: BookDetailModel
    private lateinit var dataBinding: FragmentEditBookBinding
    var year=0
    var month=0
    var day=0
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
        dataBinding.listenerDate = this
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

    override fun onDateClick(v: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        activity?.let {
                it -> DatePickerDialog(it,this, year,month,day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Calendar.getInstance().let {
            it.set(year,month,dayOfMonth)
            val date =  dayOfMonth.toString().padStart(2,'0') + '-' +
                    month.toString().padStart(2,'0') + '-' +
                    year.toString()
            dataBinding.root.txtRelease.setText(date)
            this.year = year
            this.month = month
            this.day = dayOfMonth
        }
    }
}