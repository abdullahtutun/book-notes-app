package com.example.booknotes.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.booknotes.database.Database
import com.example.booknotes.model.Book
import com.example.booknotes.model.BookWithNotes
import com.example.booknotes.model.Note

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "NotesViewModel"
    private val daoBooksWithNotes = Database.getDatabaseInstance(application).bookWithNotesDao()
    private val daoNotes = Database.getDatabaseInstance(application).notesDao()

    fun getNotes(bookName: String): LiveData<List<BookWithNotes>> = daoBooksWithNotes.getBookWithNotes(bookName)

    fun addNote(note: Note) {
        try {
            daoNotes.insertNote(note)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }

    }



}