package com.example.booknotes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.booknotes.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("SELECT * FROM Notes WHERE bookName = :bookName")
    fun getNotes(bookName: String) : List<Note>

    @Query("DELETE FROM Notes WHERE id = :id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM Notes WHERE bookName = :bookName ORDER BY " +
            "CASE WHEN :ASCorDESC = 'ASC' THEN createdDate END ASC, " +
            "CASE WHEN :ASCorDESC = 'DESC' THEN createdDate END DESC")
    fun getNotesByDateCreated(bookName: String, ASCorDESC: String) : LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE bookName = :bookName ORDER BY " +
            "CASE WHEN :ASCorDESC = 'ASC' THEN pageNo END ASC, " +
            "CASE WHEN :ASCorDESC = 'DESC' THEN pageNo END DESC")
    fun getNotesByPageNo(bookName: String, ASCorDESC: String) : LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE bookName = :bookName AND isFavorite = 1 ")
    fun getNotesByFavorite(bookName: String) : List<Note>

}