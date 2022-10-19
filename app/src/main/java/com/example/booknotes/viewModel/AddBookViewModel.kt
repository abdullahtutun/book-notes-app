package com.example.booknotes.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.booknotes.database.Database
import com.example.booknotes.model.Book
import com.example.booknotes.model.BookWithNotes

class AddBookViewModel (application: Application) : AndroidViewModel(application) {

    private val TAG = "AddBookViewModel"
    private val booksDao = Database.getDatabaseInstance(application).booksDao()
    private val bookWithNotesDao = Database.getDatabaseInstance(application).bookWithNotesDao()
    var bookColor: MutableLiveData<String> = MutableLiveData<String>("#ffe5b4")
    var bookTextColor: MutableLiveData<String> = MutableLiveData<String>("#000000")

    fun addBook(book: Book) {
        try {
            booksDao.insertBook(book)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }

    }

}