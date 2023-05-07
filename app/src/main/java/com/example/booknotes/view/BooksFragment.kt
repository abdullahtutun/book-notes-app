package com.example.booknotes.view

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booknotes.R
import com.example.booknotes.adapter.BooksAdapter
import com.example.booknotes.databinding.FragmentBooksBinding
import com.example.booknotes.viewModel.BooksViewModel
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileOutputStream
import java.text.SimpleDateFormat


@AndroidEntryPoint
class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter
    private var PERMISSION_CODE = 101
    private val STORAGE_CODE = 1001

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
        if (checkPermissions()) {
            try {
                val mDoc = Document()
                val mFileName = "asdasfas"
                val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"

                PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
                mDoc.open()

                val data = "aaaaaa"
                mDoc.addAuthor("abdullah")
                mDoc.add(Paragraph(data))
                mDoc.close()

            } catch (e: Exception){
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
            }
        } else {
            requestPermission()
        }

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

    private fun checkPermissions(): Boolean {
        val writeStoragePermission = ContextCompat.checkSelfPermission(
            requireContext(),
            WRITE_EXTERNAL_STORAGE
        )

        val readStoragePermission = ContextCompat.checkSelfPermission(
            requireContext(),
            READ_EXTERNAL_STORAGE
        )

        return writeStoragePermission == PackageManager.PERMISSION_GRANTED
                && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), PERMISSION_CODE
        )
    }

}