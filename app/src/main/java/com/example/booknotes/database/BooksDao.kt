package com.example.booknotes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.booknotes.model.Book

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)

    @Query("DELETE FROM Books WHERE id=:id")
    fun deleteBook(id: Int)

    @Update
    fun updateBook(book: Book)

    @Query("SELECT * FROM Books")
    fun getBooks(): LiveData<List<Book>>
}