package com.example.booknotes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.booknotes.database.BooksDao
import com.example.booknotes.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val dao: BooksDao) : ViewModel() {

    fun getBooks(): LiveData<List<Book>> = dao.getBooks()


}