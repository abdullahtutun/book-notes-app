package com.example.booknotes

import com.example.booknotes.helper.SharedPreferencesHelper
import com.example.booknotes.model.NoteOptions

object SessionManager {
    var sharedPreferencesHelper: SharedPreferencesHelper? = null
    var noteOptions: NoteOptions = NoteOptions()

    fun saveNoteOptionsToSharedPreferences() {
        sharedPreferencesHelper!!.putBoolean(Constant.SHARED_KEY_DATE_CREATED, noteOptions.isDateCreatedDown)
        sharedPreferencesHelper!!.putBoolean(Constant.SHARED_KEY_PAGE_NUMBER, noteOptions.isPageNumberDown)
        sharedPreferencesHelper!!.putBoolean(Constant.SHARED_KEY_FILTERING_FAVORITE, noteOptions.filteringFavorite)
    }

    fun getNoteOptionsFromSharedPreferences() {
        noteOptions.isDateCreatedDown = sharedPreferencesHelper!!.getBoolean(Constant.SHARED_KEY_DATE_CREATED, true)
        noteOptions.isPageNumberDown = sharedPreferencesHelper!!.getBoolean(Constant.SHARED_KEY_PAGE_NUMBER, true)
        noteOptions.filteringFavorite = sharedPreferencesHelper!!.getBoolean(Constant.SHARED_KEY_FILTERING_FAVORITE, false)
    }
}