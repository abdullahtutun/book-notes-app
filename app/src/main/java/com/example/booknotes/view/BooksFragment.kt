package com.example.booknotes.view

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
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
    private lateinit var binding: FragmentBooksBinding
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter

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
        binding.toolbarBooks.ivOptions.setOnClickListener(this::onOptions)
        binding.toolbarBooks.ivDelete.setOnClickListener(this::onDelete)
        binding.toolbarBooks.ivExport.setOnClickListener(this::onExport)
        binding.toolbarBooks.ivCancelClicked.setOnClickListener(this::onCancel)
    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBooks.layoutManager = layoutManager

        getBooks()
    }

    private fun onFilter(v: View){

    }

    private fun onOptions(v: View){
        val popupMenu = PopupMenu(context, v)

        popupMenu.menuInflater.inflate(R.menu.books_options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.bookOptionsAdd ->
                    addBook(v)
                R.id.bookOptionsImport ->
                    importBook()
            }
            true
        }
        popupMenu.show()
    }

    private fun onDelete(v: View){
        adapter.deleteBook(viewModel)
    }

    private fun onExport(v: View){

    }

    private fun onCancel(v: View){
        adapter.clearSelectedList()
    }

    private fun addBook(v: View){
        Navigation.findNavController(v).navigate(R.id.action_booksFragment_to_addBookFragment)
    }

    private fun importBook(){

    }

    private fun getBooks() {
        viewModel.getBooks().observe(viewLifecycleOwner) {
            it.let {
                adapter = BooksAdapter(it, binding)
                binding.rvBooks.adapter = adapter
            }
        }
    }



}