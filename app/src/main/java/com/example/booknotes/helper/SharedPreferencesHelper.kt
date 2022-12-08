package com.example.booknotes.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.booknotes.Constant

class SharedPreferencesHelper(context: Context) {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String?, value: String?) {
        editor = sharedPreferences!!.edit()
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun putInt(key: String?, value: Int) {
        editor = sharedPreferences!!.edit()
        editor!!.putInt(key, value)
        editor!!.commit()
    }

    fun putBoolean(key: String?, value: Boolean) {
        editor = sharedPreferences!!.edit()
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    fun putLong(key: String?, value: Long) {
        editor = sharedPreferences!!.edit()
        editor!!.putLong(key, value)
        editor!!.commit()
    }

    fun putFloat(key: String?, value: Float) {
        editor = sharedPreferences!!.edit()
        editor!!.putFloat(key, value)
        editor!!.commit()
    }

    fun putStringSet(key: String?, value: Set<String?>?) {
        editor = sharedPreferences!!.edit()
        editor!!.putStringSet(key, value)
        editor!!.commit()
    }

    fun getString(key: String?, value: String?): String? {
        return sharedPreferences!!.getString(key, value)
    }

    fun getInt(key: String?, value: Int): Int {
        return sharedPreferences!!.getInt(key, value)
    }

    fun getBoolean(key: String?, value: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, value)
    }

    fun getLong(key: String?, value: Long): Long {
        return sharedPreferences!!.getLong(key, value)
    }

    fun getFloat(key: String?, value: Float): Float {
        return sharedPreferences!!.getFloat(key, value)
    }

    fun getStringSet(key: String?, value: Set<String?>?): Set<String>? {
        return sharedPreferences!!.getStringSet(key, value)
    }

    fun deleteAll(){
        editor?.clear()
        editor?.commit()
    }
}