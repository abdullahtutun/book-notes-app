package com.example.booknotes.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booknotes.database.BooksDao
import com.example.booknotes.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor() : ViewModel() {
    private val TAG = "AddBookViewModel"
    var bookColor: MutableLiveData<String> = MutableLiveData<String>("#ffe5b4")
    @Inject
    lateinit var dao: BooksDao

    fun addBook(book: Book) {
        try {
            dao.insertBook(book)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }

    }

}