package com.example.booknotes.model

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithNotes(
    @Embedded val book: Book,
    @Relation(
        parentColumn = "bookName",
        entityColumn = "bookName"
    )
    val notes: List<Note>
)