package com.example.booknotes.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booknotes.R
import com.example.booknotes.adapter.NotesAdapter
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.helpers.MessageDialogHelper
import com.example.booknotes.model.Note
import com.example.booknotes.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {
    val bookOfNotes by navArgs<NotesFragmentArgs>()
    lateinit var binding: FragmentNotesBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater,container,false)

        init()
        initAdapter()

        return binding.root
    }

    private fun init(){
        binding.fabNotes.setOnClickListener(this::onFabNotes)
        binding.toolbarNotes.ivBack.setOnClickListener(this::onBack)
        binding.toolbarNotes.ivFilter.setOnClickListener(this::onFilter)
        binding.toolbarNotes.ivCopy.setOnClickListener(this::onCopy)
        binding.toolbarNotes.ivDelete.setOnClickListener(this::onDelete)
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.layoutManager = layoutManager

        getNotes()
    }

    private fun getNotes() {
        viewModel.getNotes(bookOfNotes.data).observe(viewLifecycleOwner) {
            adapter = NotesAdapter(requireContext(), it[0].notes, binding)
            binding.rvNotes.adapter = adapter
            Log.d("aaa", it.size.toString())
        }
    }

    private fun onFabNotes(v: View){
        MessageDialogHelper.addNoteDialog(this.context, object : MessageDialogHelper.MessageDialogListener {
            override fun onPositiveButtonClicked(dialog:Dialog, note: TextView, pageNumber: TextView) {
                var note = note.text.toString()
                var pageNumber = pageNumber.text.toString()
                var book = bookOfNotes.data

                if(checkFields(note, pageNumber)){
                    var noteObj = Note(null, note, pageNumber.toInt(), book)
                    viewModel.addNote(noteObj)
                    dialog?.dismiss()
                }
            }
            override fun onNegativeButtonClicked() {

            }

        })
    }

    private fun checkFields(note: String, pageNumber: String): Boolean{
        if(note.isNullOrBlank()){
            Toast.makeText(requireContext(),R.string.warning_note, Toast.LENGTH_SHORT).show()
            return false
        }
        if(pageNumber.isNullOrEmpty()){
            Toast.makeText(requireContext(),R.string.warning_note_page_number, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun onBack(v: View){
        binding.root.findNavController().popBackStack(R.id.booksFragment,false)
    }

    private fun onFilter(v: View){

    }

    private fun onCopy(v: View){

    }

    private fun onCancel(v: View){

    }

    private fun onDelete(v: View){
        adapter.deleteNote(viewModel)
    }

    private fun setOnClickEvents(){}




}