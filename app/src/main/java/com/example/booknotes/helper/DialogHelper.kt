package com.example.booknotes.helper

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.booknotes.R
import com.example.booknotes.SessionManager

object DialogHelper {

    fun addNoteDialog(context: Context,listener: MessageDialogListener?) {

        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setContentView(R.layout.dialog_add_note)

        val btnSave = dialog?.findViewById(R.id.ivSaveEt) as ImageView
        val btnCancel = dialog?.findViewById(R.id.ivCancelEt) as ImageView
        val note = dialog?.findViewById(R.id.dialogNote) as TextView
        val pageNumber = dialog?.findViewById(R.id.dialogPageNumber) as TextView

        btnSave.setOnClickListener {
            listener?.onPositiveButtonClicked(dialog, note,pageNumber)
        }

        btnCancel.setOnClickListener {
            listener?.onNegativeButtonClicked()
            dialog?.dismiss()
        }

        dialog?.show()
    }


    interface MessageDialogListener {
        fun onPositiveButtonClicked(dialog: Dialog, note:TextView, pageNumber:TextView)
        fun onNegativeButtonClicked()
    }

}