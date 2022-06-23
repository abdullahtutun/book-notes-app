package com.example.booknotes.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.booknotes.R
import com.example.booknotes.databinding.FragmentAddBookBinding

class AddBookFragment : Fragment() {

    lateinit var binding: FragmentAddBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBookBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    fun onSave(view:View){

    }

    fun onExit(view:View){

    }

}