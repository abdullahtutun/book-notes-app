package com.example.booknotes.di

import android.content.Context
import com.example.booknotes.database.BooksDao
import com.example.booknotes.database.Database
import com.example.booknotes.database.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): Database {
        return Database.getDatabaseInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideNotesDao(db: Database): NotesDao{
        return db.notesDao()
    }

    @Singleton
    @Provides
    fun provideBooksDao(db: Database): BooksDao{
        return db.booksDao()
    }
}