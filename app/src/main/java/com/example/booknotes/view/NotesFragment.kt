package com.example.booknotes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.booknotes.R
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.helpers.MessageDialogHelper

class NotesFragment : Fragment() {
    lateinit var binding: FragmentNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater,container,false)

        init()

        return binding.root
    }

    private fun init(){
        binding.fabNotes.setOnClickListener(this::onFabNotes)
        binding.toolbarNotes.ivBack.setOnClickListener(this::onBack)
        binding.toolbarNotes.ivSave.setOnClickListener(this::onSave)
    }

    private fun onBack(v: View){
        binding.root.findNavController().popBackStack(R.id.booksFragment,false)
    }

    private fun onSave(v: View){

    }

    private fun onFabNotes(v: View){
        MessageDialogHelper.showAddNoteDialog(
            this.context,
            object : MessageDialogHelper.MessageDialogListener {
                override fun onPositiveButtonClicked() {

                }
                override fun onNegativeButtonClicked() {

                }
            })
    }
}