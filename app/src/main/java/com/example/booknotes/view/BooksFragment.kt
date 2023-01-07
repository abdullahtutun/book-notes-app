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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment() {
    lateinit var binding: FragmentBooksBinding
    private val viewModel: BooksViewModel by viewModels()
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
        binding.toolbarBooks.ivAdd.setOnClickListener(this::onAdd)

    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(requireContext(),2,)
        binding.rvBooks.layoutManager = layoutManager

        getBooks()
    }

    private fun onAdd(v: View){
        Navigation.findNavController(v).navigate(R.id.action_booksFragment_to_addBookFragment)
    }

    private fun onFilter(v: View){

    }

    private fun getBooks() {
        viewModel.getBooks().observe(viewLifecycleOwner) {
            it.let {
                adapter = BooksAdapter(it)
                binding.rvBooks.adapter = adapter
            }
        }
    }



}