package com.example.booknotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.booknotes.model.BookWithNotes

@Dao
interface BookWithNotesDao {
    @Transaction
    @Query("SELECT * FROM Books WHERE bookName = :bookName")
    fun getBookWithNotes(bookName: String) : LiveData<List<BookWithNotes>>

}

// notlar sayfasına kitap verisinin de gelmesine bakacaksın