package com.example.booknotes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.booknotes.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotesBinding.inflate(layoutInflater,container,false)

        binding.toolbarNotes.setNavigationOnClickListener {
            Toast.makeText(requireContext(),"tıklandı", Toast.LENGTH_SHORT).show()
        }





        return binding.root
    }
}