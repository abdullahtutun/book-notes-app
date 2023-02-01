package com.example.booknotes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booknotes.database.NotesDao
import com.example.booknotes.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val daoNotes: NotesDao): ViewModel() {

    private val TAG = "NotesViewModel"

    fun getNotes(bookName: String): List<Note>{
        var noteList: List<Note> = emptyList()
        try {
            noteList = daoNotes.getNotes(bookName)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
        return noteList
    }

    fun getNotesByDateCreated(bookName: String, ASCorDESC: String): LiveData<List<Note>>{
        var noteList: LiveData<List<Note>> = MutableLiveData()
        try {
            noteList = daoNotes.getNotesByDateCreated(bookName, ASCorDESC)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
        return noteList
    }

    fun getNotesByPageNo(bookName: String, ASCorDESC: String): LiveData<List<Note>>{
        var noteList: LiveData<List<Note>> = MutableLiveData()
        try {
            noteList = daoNotes.getNotesByPageNo(bookName, ASCorDESC)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
        return noteList
    }

    fun getNotesByFavorite(bookName: String): List<Note>{
        var noteList: List<Note> = emptyList()
        try {
            noteList = daoNotes.getNotesByFavorite(bookName)

        } catch (e:Exception){
            Log.e(TAG, e.message, e)
        }
        return noteList
    }

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