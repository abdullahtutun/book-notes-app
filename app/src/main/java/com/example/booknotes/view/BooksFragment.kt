package com.example.booknotes.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.booknotes.R
import com.example.booknotes.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    lateinit var binding: FragmentBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        return binding.root

    }

}