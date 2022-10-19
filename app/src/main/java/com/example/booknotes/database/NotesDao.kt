package com.example.booknotes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.booknotes.model.Book
import com.example.booknotes.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(note: Note)



}