package com.example.booknotes.helpers

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.booknotes.R
import com.example.booknotes.model.Note
import com.example.booknotes.viewModel.NotesViewModel

object MessageDialogHelper {

    fun addNoteDialog(context: Context?, vm: NotesViewModel, bookName: String) {
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
            val note = note.text.toString()
            val pageNumber = pageNumber.text.toString().toInt()
            val noteObj: Note = Note(null, note, pageNumber, bookName)
            vm.addNote(noteObj)
            dialog?.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        dialog?.show()
    }

}