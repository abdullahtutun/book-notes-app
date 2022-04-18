package com.example.booknotes.fragment

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

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBookBinding.inflate(layoutInflater,container,false)

        binding.toolbarAddBook.setNavigationOnClickListener {
            Toast.makeText(requireContext(),"tıklandı",Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_book_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addBookSave -> {
                Log.d("Etiket","tıklandı")
                Toast.makeText(requireContext(),"tıklandı", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}