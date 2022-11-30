package com.example.booknotes.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booknotes.database.BooksDao
import com.example.booknotes.database.Database
import com.example.booknotes.database.NotesDao
import com.example.booknotes.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(private val dao: BooksDao) : ViewModel() {
    private val TAG = "AddBookViewModel"
    var bookColor: MutableLiveData<String> = MutableLiveData<String>("#ffe5b4")
    var bookTextColor: MutableLiveData<String> = MutableLiveData<String>("#000000")

    fun addBook(book: Book) {
        try {
            dao.insertBook(book)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }

    }

}