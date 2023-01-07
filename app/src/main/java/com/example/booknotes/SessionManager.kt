package com.example.booknotes

import com.example.booknotes.helper.SharedPreferencesHelper
import com.example.booknotes.model.NoteSortingOptions
import javax.inject.Inject

class SessionManager @Inject constructor(var sharedPreferencesHelper: SharedPreferencesHelper) {
    var noteSortingOptions: NoteSortingOptions = NoteSortingOptions()

    fun saveNoteOptionsToSharedPreferences() {
        sharedPreferencesHelper.putBoolean(Constant.SHARED_KEY_DATE_CREATED, noteSortingOptions.isDateCreatedDown)
        sharedPreferencesHelper.putBoolean(Constant.SHARED_KEY_PAGE_NUMBER, noteSortingOptions.isPageNumberDown)
        sharedPreferencesHelper.putBoolean(Constant.SHARED_KEY_FILTERING_FAVORITE, noteSortingOptions.filteringFavorite)
    }

    fun getNoteOptionsFromSharedPreferences() {
        noteSortingOptions.isDateCreatedDown = sharedPreferencesHelper.getBoolean(Constant.SHARED_KEY_DATE_CREATED, true)
        noteSortingOptions.isPageNumberDown = sharedPreferencesHelper.getBoolean(Constant.SHARED_KEY_PAGE_NUMBER, true)
        noteSortingOptions.filteringFavorite = sharedPreferencesHelper.getBoolean(Constant.SHARED_KEY_FILTERING_FAVORITE, false)
    }
}