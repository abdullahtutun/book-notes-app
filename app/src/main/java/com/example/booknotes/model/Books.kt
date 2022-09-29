package com.example.booknotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Books")
data class Books(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var bookName: String?,
    var bookAuthor: String?,
    var bookGenre: String?,
    var noteCount: Int?,
): Parcelable