package com.example.booknotes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.booknotes.database.BookWithNotesDao
import com.example.booknotes.database.NotesDao
import com.example.booknotes.model.BookWithNotes
import com.example.booknotes.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val daoNotes: NotesDao,
    private val daoBooksWithNotes: BookWithNotesDao
) : ViewModel() {

    private val TAG = "NotesViewModel"

    fun getNotes(bookName: String): LiveData<List<BookWithNotes>> = daoBooksWithNotes.getBookWithNotes(bookName)

    fun addNote(note: Note) {
        try {
            daoNotes.insertNote(note)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
    }

    fun deleteNote(id: Int){
        try {
            daoNotes.deleteNote(id)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
    }

    fun updateNote(note: Note){
        try {
            daoNotes.updateNote(note)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
    }

}