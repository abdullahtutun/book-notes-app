package com.example.booknotes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.booknotes.database.Database
import com.example.booknotes.model.Book

class BooksViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = Database.getDatabaseInstance(application).booksDao()

    fun getBooks(): LiveData<List<Book>> = dao.getBooks()

}