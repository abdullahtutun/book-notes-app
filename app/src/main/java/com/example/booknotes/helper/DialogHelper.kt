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

    fun showBottomSheetDialog(context: Context, listener: BottomSheetDialogListener) {

        val dialog = context?.let { Dialog(it) }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_notes)

        val llSorting = dialog.findViewById(R.id.llSorting) as LinearLayout
        val llFiltering = dialog.findViewById(R.id.llFiltering) as LinearLayout
        val llSortingOptions = dialog.findViewById(R.id.llSortingOptions) as LinearLayout
        val llFilteringOptions = dialog.findViewById(R.id.llFilteringOptions) as LinearLayout
        val llDateCreated = dialog.findViewById(R.id.llDateCreated) as LinearLayout
        val llPageNumber = dialog.findViewById(R.id.llPageNumber) as LinearLayout
        val ivPageNumberDown = dialog.findViewById(R.id.ivPageNumberDown) as ImageView
        val ivPageNumberUp = dialog.findViewById(R.id.ivPageNumberUp) as ImageView
        val ivDateCreatedUp = dialog.findViewById(R.id.ivDateCreatedUp) as ImageView
        val ivDateCreatedDown = dialog.findViewById(R.id.ivDateCreatedDown) as ImageView
        val cbFavorite = dialog.findViewById(R.id.cbFavorite) as CheckBox
        val buttonOkey = dialog.findViewById(R.id.buttonOkey) as Button

        if(SessionManager.noteOptions.isDateCreatedDown){
            ivDateCreatedDown.visibility = View.VISIBLE
            ivDateCreatedUp.visibility = View.GONE

        } else {
            ivDateCreatedDown.visibility = View.GONE
            ivDateCreatedUp.visibility = View.VISIBLE
        }

        if(SessionManager.noteOptions.isPageNumberDown){
            ivPageNumberDown.visibility = View.VISIBLE
            ivPageNumberUp.visibility = View.GONE

        } else {
            ivPageNumberDown.visibility = View.GONE
            ivPageNumberUp.visibility = View.VISIBLE
        }

        cbFavorite.isChecked = SessionManager.noteOptions.filteringFavorite

        //listener.setChoicesOfUser(ivDateCreatedDown, ivDateCreatedDown, ivPageNumberDown, ivPageNumberUp, cbFavorite)

        llSorting.setOnClickListener{
            listener.onLlSortingClicked(llSortingOptions)
        }

        llFiltering.setOnClickListener{
            listener.onLlFilteringClicked(llFilteringOptions)
        }

        cbFavorite.setOnCheckedChangeListener { _, isChecked ->
            listener.onCbFavoriteClicked(cbFavorite, isChecked)
        }

        llDateCreated.setOnClickListener {
            listener.onLlDateCreatedClicked(ivDateCreatedDown, ivDateCreatedUp)
        }

        llPageNumber.setOnClickListener {
            listener.onLlPageNumberClicked(ivPageNumberDown, ivPageNumberUp)
        }

        buttonOkey.setOnClickListener {
            listener.onButtonOkeyClicked(dialog)
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }


    interface MessageDialogListener {
        fun onPositiveButtonClicked(dialog: Dialog, note:TextView, pageNumber:TextView)
        fun onNegativeButtonClicked()
    }

    interface BottomSheetDialogListener {
        //fun setChoicesOfUser(ivDateCreatedDown: ImageView, ivDateCreatedUp: ImageView, ivPageNumberDown: ImageView, ivPageNumberUp: ImageView, cbFavorite: CheckBox)
        fun onLlSortingClicked(llSortingOptions: LinearLayout)
        fun onLlFilteringClicked(llFilteringOptions: LinearLayout)
        fun onCbFavoriteClicked(cbFavorite: CheckBox, isChecked: Boolean)
        fun onLlDateCreatedClicked(ivDateCreatedDown: ImageView, ivDateCreatedUp: ImageView)
        fun onLlPageNumberClicked(ivPageNumberDown: ImageView, ivPageNumberUp: ImageView)
        fun onButtonOkeyClicked(dialog: Dialog)

    }

}