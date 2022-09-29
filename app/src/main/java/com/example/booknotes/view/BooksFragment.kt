package com.example.booknotes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.booknotes.R
import com.example.booknotes.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    lateinit var binding: FragmentBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksBinding.inflate(layoutInflater,container,false)

        init()

        return binding.root

    }

    private fun init(){
        binding.toolbarBooks.ivFilter.setOnClickListener(this::onFilter)
        binding.toolbarBooks.ivSave.setOnClickListener(this::onSave)

    }

    private fun onFilter(v: View){
        Navigation.findNavController(v).navigate(R.id.action_booksFragment_to_notesFragment)
    }

    private fun onSave(v: View){
        Navigation.findNavController(v).navigate(R.id.action_booksFragment_to_addBookFragment)
    }

}