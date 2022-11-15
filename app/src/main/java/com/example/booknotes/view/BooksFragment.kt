package com.example.booknotes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booknotes.R
import com.example.booknotes.adapter.BooksAdapter
import com.example.booknotes.databinding.FragmentBooksBinding
import com.example.booknotes.viewModel.BooksViewModel

class BooksFragment : Fragment() {

    lateinit var binding: FragmentBooksBinding
    val viewModel: BooksViewModel by viewModels()
    lateinit var adapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksBinding.inflate(layoutInflater,container,false)

        init()
        initAdapter()

        return binding.root
    }

    private fun init(){
        binding.toolbarBooks.ivFilter.setOnClickListener(this::onFilter)
        binding.toolbarBooks.ivSave.setOnClickListener(this::onSave)

    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(requireContext(),2,)
        binding.rvBooks.layoutManager = layoutManager

        getBooks()
    }

    private fun getBooks() {
        viewModel.getBooks().observe(viewLifecycleOwner) {
            adapter = BooksAdapter(requireContext(), it)
            binding.rvBooks.adapter = adapter
        }
    }

    private fun onFilter(v: View){

    }

    private fun onSave(v: View){
        Navigation.findNavController(v).navigate(R.id.action_booksFragment_to_addBookFragment)
    }

}