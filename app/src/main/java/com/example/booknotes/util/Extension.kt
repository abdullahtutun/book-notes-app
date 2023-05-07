package com.example.booknotes.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Color
import com.example.booknotes.R
import kotlin.math.sqrt

fun String.copyToClipboard(context: Context){
    val clip = ClipData.newPlainText(R.string.coppied_note.toString(), this)
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(clip)
}

fun Int.isBrightColor(): Boolean{
    if (android.R.color.transparent == this)
        return true
    var rtnValue = false
    val rgb = intArrayOf(Color.red(this), Color.green(this), Color.blue(this))
    val brightness = sqrt(
        rgb[0] * rgb[0] * .241 + (rgb[1]
                * rgb[1] * .691) + rgb[2] * rgb[2] * .068
    ).toInt()

    // color is light
    if (brightness >= 200) {
        rtnValue = true
    }
    return rtnValue
}