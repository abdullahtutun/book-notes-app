package com.example.booknotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var note: String?,
    var pageNo: Int?,
    var bookName: String?,
    var isFavorite: Int?,
    var createdDate: String?
): Parcelable
