package com.example.booknotes.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booknotes.model.Book
import com.example.booknotes.model.Note

@androidx.room.Database(entities = [Book::class, Note::class], version = 1, exportSchema = false)
abstract class Database  : RoomDatabase(){

    abstract fun notesDao() : NotesDao
    abstract fun booksDao() : BooksDao

    companion object {
        @Volatile
        var INSTANCE: Database? = null

        fun getDatabaseInstance(context: Context): Database {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance = Room.databaseBuilder(context.applicationContext,Database::class.java,"Notes").allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance

                return roomDatabaseInstance
            }
        }
    }
}