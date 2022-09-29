package com.example.booknotes.database

import androidx.room.*
import com.example.booknotes.model.Books

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(books: Books)

    @Query("DELETE FROM Books WHERE id=:id")
    fun deleteBook(id: Int)

    @Update
    fun updateNote(books: Books)
}