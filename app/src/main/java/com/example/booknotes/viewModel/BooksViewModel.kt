package com.example.booknotes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.booknotes.database.BooksDao
import com.example.booknotes.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val dao: BooksDao) : ViewModel() {
    private val TAG = "BooksViewModel"

    fun getBooks(): LiveData<List<Book>> = dao.getBooks()

    fun deleteBook(id: Int){
        try {
            dao.deleteBook(id)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
    }
}