package com.example.booknotes.helper

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private var sharedPreferences: SharedPreferences,
    private var editor: SharedPreferences.Editor
) {

    fun putString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun putInt(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun putBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putLong(key: String?, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    fun putFloat(key: String?, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    fun putStringSet(key: String?, value: Set<String?>?) {
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun getString(key: String?, value: String?): String? {
        return sharedPreferences.getString(key, value)
    }

    fun getInt(key: String?, value: Int): Int {
        return sharedPreferences.getInt(key, value)
    }

    fun getBoolean(key: String?, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, value)
    }

    fun getLong(key: String?, value: Long): Long {
        return sharedPreferences.getLong(key, value)
    }

    fun getFloat(key: String?, value: Float): Float {
        return sharedPreferences.getFloat(key, value)
    }

    fun getStringSet(key: String?, value: Set<String?>?): Set<String>? {
        return sharedPreferences.getStringSet(key, value)
    }

    fun deleteAll(){
        editor.clear()
        editor.apply()
    }
}