package com.example.booknotes.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booknotes.R
import com.example.booknotes.adapter.BooksAdapter
import com.example.booknotes.adapter.NotesAdapter
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.helpers.MessageDialogHelper
import com.example.booknotes.viewModel.NotesViewModel

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
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.layoutManager = layoutManager

        getNotes()
    }

    private fun getNotes() {
        viewModel.getNotes(bookOfNotes.data).observe(viewLifecycleOwner) {
            adapter = NotesAdapter(requireContext(), it[0].notes)
            binding.rvNotes.adapter = adapter
            Log.d("aaa", it.size.toString())
        }
    }

    private fun onBack(v: View){
        binding.root.findNavController().popBackStack(R.id.booksFragment,false)
    }

    private fun onFilter(v: View){

    }

    private fun onFabNotes(v: View){
        MessageDialogHelper.addNoteDialog(this.context, viewModel, bookOfNotes.data)
    }
}