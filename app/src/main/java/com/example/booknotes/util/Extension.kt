package com.example.booknotes.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import com.example.booknotes.R

fun String.copyToClipboard(context: Context){
    val clip = ClipData.newPlainText(R.string.coppied_note.toString(), this)
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(clip)
}