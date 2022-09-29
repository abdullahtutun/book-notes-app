package com.example.booknotes.database

import androidx.room.*
import com.example.booknotes.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(notes: Notes)


}