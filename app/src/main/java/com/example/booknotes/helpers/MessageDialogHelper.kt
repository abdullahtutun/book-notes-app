package com.example.booknotes.helpers

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import com.example.booknotes.R

object MessageDialogHelper {

    fun showAddNoteDialog(context: Context?, listener: MessageDialogListener?) {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.dialog_add_note)
        val btnSave = dialog?.findViewById(R.id.ivSaveEt) as ImageView
        val btnCancel = dialog?.findViewById(R.id.ivCancelEt) as ImageView
        btnSave.setOnClickListener {
            listener?.onPositiveButtonClicked()
            dialog?.dismiss()
        }

        btnCancel.setOnClickListener {
            listener?.onNegativeButtonClicked()
            dialog?.dismiss()
        }
        dialog?.show()
    }
    interface MessageDialogListener {
        fun onPositiveButtonClicked()
        fun onNegativeButtonClicked()
    }
}