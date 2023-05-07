package com.example.booknotes.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.booknotes.R
import com.example.booknotes.databinding.FragmentAddBookBinding
import com.example.booknotes.model.Book
import com.example.booknotes.util.isBrightColor
import com.example.booknotes.viewModel.AddBookViewModel
import dagger.hilt.android.AndroidEntryPoint
import top.defaults.colorpicker.ColorPickerPopup
import kotlin.math.sqrt

@AndroidEntryPoint
class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel: AddBookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBookBinding.inflate(layoutInflater,container,false)

        init()

        return binding.root
    }
    
    private fun init(){
        binding.toolbarAddBook.ivExit.setOnClickListener(this::onExit)
        binding.toolbarAddBook.ivSave.setOnClickListener(this::onSave)
        binding.btnColorPalette.setOnClickListener(this::onBtnColorPalette)

        setBookColor()
        setBookText()
    }

    private fun onBtnColorPalette(v: View){
        ColorPickerPopup.Builder(activity)
            .initialColor(Color.parseColor("#ffe5b4"))
            .enableBrightness(true)
            .enableAlpha(true)
            .okTitle(getString(R.string.choose))
            .cancelTitle(getString(R.string.cancel))
            .showIndicator(true)
            .showValue(false)
            .build()
            .show(v, object : ColorPickerPopup.ColorPickerObserver {
                override fun onColor(color: Int, fromUser: Boolean) {
                    viewModel.bookColor.value = color
                }

                override fun onColorPicked(color: Int) {
                    viewModel.bookColor.value = color
                }

            })
    }

    private fun setBookColor() {
        viewModel.bookColor.observe(viewLifecycleOwner) {
            binding.includeBook.clCard.setBackgroundColor(it)

            if(it.isBrightColor()) {
                binding.includeBook.bookName.setTextColor(Color.BLACK)
                binding.includeBook.bookAuthor.setTextColor(Color.BLACK)
                binding.includeBook.bookGenre.setTextColor(Color.BLACK)
            } else {
                binding.includeBook.bookName.setTextColor(Color.WHITE)
                binding.includeBook.bookAuthor.setTextColor(Color.WHITE)
                binding.includeBook.bookGenre.setTextColor(Color.WHITE)
            }
        }

    }

    private fun setBookText() {
        binding.editTextBook.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.includeBook.bookName.text = s.toString()
            }

        })

        binding.editTextAuthor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.includeBook.bookAuthor.text = s.toString()
            }

        })

        binding.editTextGenre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.includeBook.bookGenre.text = s.toString()
            }

        })
    }

    private fun onExit(v: View){
        binding.root.findNavController().popBackStack(R.id.booksFragment, false)
    }

    private fun onSave(v: View){
        if(checkFields()){
            val bookColor = viewModel.bookColor.value
            val bookName = binding.editTextBook.text.toString()
            val bookAuthor = binding.editTextAuthor.text.toString()
            val bookGenre = binding.editTextGenre.text.toString()

            val book = Book(null, bookName, bookAuthor, bookGenre, bookColor, 0)
            viewModel.addBook(book)
            binding.root.findNavController().popBackStack()
        }
    }

    private fun checkFields(): Boolean{
        if (binding.editTextBook.text.toString().trim().isEmpty()){
            Toast.makeText(requireContext(),R.string.warning_book,Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.editTextAuthor.text.toString().trim().isEmpty()){
            Toast.makeText(requireContext(),R.string.warning_book_author,Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.editTextGenre.text.toString().trim().isEmpty()){
            Toast.makeText(requireContext(),R.string.warning_book_genre,Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }





}