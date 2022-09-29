package com.example.booknotes.model

import androidx.room.Embedded
import androidx.room.Relation

data class BooksWithNotes(
    @Embedded val book: Books,
    @Relation(
        parentColumn = "bookName",
        entityColumn = "bookName"
    )
    val notes: List<Notes>
)