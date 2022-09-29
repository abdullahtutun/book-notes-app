package com.example.booknotes.view

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.booknotes.R
import com.example.booknotes.databinding.FragmentAddBookBinding
import com.example.booknotes.viewModel.AddBookViewModel

class AddBookFragment : Fragment() {

    lateinit var binding: FragmentAddBookBinding
    val viewModel: AddBookViewModel by viewModels()

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
        binding.color1.setOnClickListener(this::onColor1)
        binding.color2.setOnClickListener(this::onColor2)
        binding.color3.setOnClickListener(this::onColor3)
        binding.color4.setOnClickListener(this::onColor4)
        binding.color5.setOnClickListener(this::onColor5)
        binding.color6.setOnClickListener(this::onColor6)

        setBookColor()
        setBookText()
    }

    private fun onExit(v: View){
        binding.root.findNavController().popBackStack(R.id.booksFragment, false)
    }

    private fun onSave(v: View){

    }

    private fun setBookColor() {
        viewModel.bookColor.observe(viewLifecycleOwner, {
            when(it){
                "#ffe5b4" -> {
                    binding.includeBook.clCard.setBackgroundColor(Color.parseColor("#ffe5b4"))
                    binding.includeBook.bookName.setTextColor(Color.BLACK)
                    binding.includeBook.bookAuthor.setTextColor(Color.BLACK)
                    binding.includeBook.bookTag.setTextColor(Color.BLACK)
                }
                "#F38181" -> {
                    binding.includeBook.clCard.setBackgroundColor(Color.parseColor("#F38181"))
                    binding.includeBook.bookName.setTextColor(Color.BLACK)
                    binding.includeBook.bookAuthor.setTextColor(Color.BLACK)
                    binding.includeBook.bookTag.setTextColor(Color.BLACK)
                }
                "#393E46" -> {
                    binding.includeBook.clCard.setBackgroundColor(Color.parseColor("#393E46"))
                    binding.includeBook.bookName.setTextColor(Color.WHITE)
                    binding.includeBook.bookAuthor.setTextColor(Color.WHITE)
                    binding.includeBook.bookTag.setTextColor(Color.WHITE)

                }
                "#EAFFD0" -> {
                    binding.includeBook.clCard.setBackgroundColor(Color.parseColor("#EAFFD0"))
                    binding.includeBook.bookName.setTextColor(Color.BLACK)
                    binding.includeBook.bookAuthor.setTextColor(Color.BLACK)
                    binding.includeBook.bookTag.setTextColor(Color.BLACK)
                }
                "#95E1D3" -> {
                    binding.includeBook.clCard.setBackgroundColor(Color.parseColor("#95E1D3"))
                    binding.includeBook.bookName.setTextColor(Color.BLACK)
                    binding.includeBook.bookAuthor.setTextColor(Color.BLACK)
                    binding.includeBook.bookTag.setTextColor(Color.BLACK)
                }
                "#3F72AF" -> {
                    binding.includeBook.clCard.setBackgroundColor(Color.parseColor("#3F72AF"))
                    binding.includeBook.bookName.setTextColor(Color.BLACK)
                    binding.includeBook.bookAuthor.setTextColor(Color.BLACK)
                    binding.includeBook.bookTag.setTextColor(Color.BLACK)
                }
            }
        })

    }

    private fun setBookText() {
        binding.editTextBook.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.includeBook.bookName.setText(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.editTextAuthor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.includeBook.bookAuthor.setText(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.editTextTag.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.includeBook.bookTag.setText(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun onColor1(v: View){
        binding.color1.setImageResource(R.drawable.ic_done)
        binding.color2.setImageResource(0)
        binding.color3.setImageResource(0)
        binding.color4.setImageResource(0)
        binding.color5.setImageResource(0)
        binding.color6.setImageResource(0)
        viewModel.bookColor.value = "#ffe5b4"
    }

    private fun onColor2(v: View){
        binding.color2.setImageResource(R.drawable.ic_done)
        binding.color1.setImageResource(0)
        binding.color3.setImageResource(0)
        binding.color4.setImageResource(0)
        binding.color5.setImageResource(0)
        binding.color6.setImageResource(0)
        viewModel.bookColor.value = "#F38181"
    }

    private fun onColor3(v: View){
        binding.color3.setImageResource(R.drawable.ic_done)
        binding.color2.setImageResource(0)
        binding.color1.setImageResource(0)
        binding.color4.setImageResource(0)
        binding.color5.setImageResource(0)
        binding.color6.setImageResource(0)
        viewModel.bookColor.value = "#393E46"
    }

    private fun onColor4(v: View){
        binding.color4.setImageResource(R.drawable.ic_done)
        binding.color2.setImageResource(0)
        binding.color3.setImageResource(0)
        binding.color1.setImageResource(0)
        binding.color5.setImageResource(0)
        binding.color6.setImageResource(0)
        viewModel.bookColor.value = "#EAFFD0"
    }

    private fun onColor5(v: View){
        binding.color5.setImageResource(R.drawable.ic_done)
        binding.color2.setImageResource(0)
        binding.color3.setImageResource(0)
        binding.color4.setImageResource(0)
        binding.color1.setImageResource(0)
        binding.color6.setImageResource(0)
        viewModel.bookColor.value = "#95E1D3"
    }

    private fun onColor6(v: View){
        binding.color6.setImageResource(R.drawable.ic_done)
        binding.color2.setImageResource(0)
        binding.color3.setImageResource(0)
        binding.color4.setImageResource(0)
        binding.color5.setImageResource(0)
        binding.color1.setImageResource(0)
        viewModel.bookColor.value = "#3F72AF"
    }



}