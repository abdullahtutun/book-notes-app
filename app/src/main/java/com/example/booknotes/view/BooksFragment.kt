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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.books_options_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.bookOptionsAdd){
            Log.d("Etiket","tıklandı")
            Toast.makeText(requireContext(),"tıklandı",Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

}