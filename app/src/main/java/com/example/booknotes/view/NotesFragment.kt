package com.example.booknotes.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booknotes.R
import com.example.booknotes.adapter.NotesAdapter
import com.example.booknotes.databinding.BottomSheetLayoutNotesBinding
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.helpers.DialogHelper
import com.example.booknotes.model.Note
import com.example.booknotes.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {
    val bookOfNotes by navArgs<NotesFragmentArgs>()
    lateinit var binding: FragmentNotesBinding
    lateinit var bindingBottomSheet: BottomSheetLayoutNotesBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater,container,false)
        bindingBottomSheet = BottomSheetLayoutNotesBinding.inflate(layoutInflater,container,false)

        init()
        initAdapter()

        return binding.root
    }

    private fun init(){
        binding.fabNotes.setOnClickListener(this::onFabNotes)
        binding.toolbarNotes.ivBack.setOnClickListener(this::onBack)
        binding.toolbarNotes.ivFilter.setOnClickListener(this::onFilter)
        binding.toolbarNotes.ivCopy.setOnClickListener(this::onCopy)
        binding.toolbarNotes.ivDelete.setOnClickListener(this::onDelete)
        binding.toolbarNotes.ivCancelClicked.setOnClickListener(this::onCancel)
        binding.toolbarNotes.ivStar.setOnClickListener(this::onStar)
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.layoutManager = layoutManager

        getNotes()
    }

    private fun onBack(v: View){
        binding.root.findNavController().popBackStack(R.id.booksFragment,false)
    }

    private fun onFilter(v: View){
        showBottomSheetDialog()
    }

    private fun onCopy(v: View){
        adapter.copyNote()
    }

    private fun onCancel(v: View){
        adapter.clearSelectedList()
    }

    private fun onDelete(v: View){
        adapter.deleteNote(viewModel)
    }

    private fun onStar(v: View){
        adapter.setStarOfNote(viewModel)
    }

    private fun getNotes() {
        viewModel.getNotes(bookOfNotes.data).observe(viewLifecycleOwner) {
            it.let {
                adapter = NotesAdapter(requireContext(), it[0].notes, binding)
                binding.rvNotes.adapter = adapter
            }
        }
    }

    private fun onFabNotes(v: View){
        DialogHelper.addNoteDialog(requireContext(), object : DialogHelper.MessageDialogListener {
            override fun onPositiveButtonClicked(dialog:Dialog, note: TextView, pageNumber: TextView) {
                var note = note.text.toString()
                var pageNumber = pageNumber.text.toString()
                var book = bookOfNotes.data

                if(checkFields(note, pageNumber)){
                    var noteObj = Note(null, note, pageNumber.toInt(), book, false)
                    viewModel.addNote(noteObj)
                    adapter.clearSelectedList()
                    dialog?.dismiss()
                }
            }
            override fun onNegativeButtonClicked() {

            }
        })
    }

    private fun showBottomSheetDialog(){
        var isOpenSortingOptions = false
        var isOpenFilteringOptions = false
        var isSortingOptionsDown = true
        var isFilteringOptionsDown = true

        DialogHelper.showBottomSheetDialog(requireContext(), object : DialogHelper.BottomSheetDialogListener {
            override fun onLlSortingClicked(llSortingOptions: LinearLayout) {
                if(!isOpenSortingOptions){
                    llSortingOptions.visibility = View.VISIBLE
                    isOpenSortingOptions = true

                } else {
                    llSortingOptions.visibility = View.GONE
                    isOpenSortingOptions = false
                }
            }

            override fun onLlFilteringClicked(llFilteringOptions: LinearLayout) {
                if(!isOpenFilteringOptions){
                    llFilteringOptions.visibility = View.VISIBLE
                    isOpenFilteringOptions = true

                } else {
                    llFilteringOptions.visibility = View.GONE
                    isOpenFilteringOptions = false
                }
            }

            override fun onLlDateCreatedClicked(ivDateCreatedDown: ImageView, ivDateCreatedUp: ImageView) {
                if(isSortingOptionsDown){
                    ivDateCreatedDown.visibility = View.GONE
                    ivDateCreatedUp.visibility = View.VISIBLE
                    isSortingOptionsDown = false

                } else {
                    ivDateCreatedDown.visibility = View.VISIBLE
                    ivDateCreatedUp.visibility = View.GONE
                    isSortingOptionsDown = true
                }
            }

            override fun onLlPageNumberClicked(ivPageNumberDown: ImageView, ivPageNumberUp: ImageView) {
                if(isFilteringOptionsDown){
                    ivPageNumberDown.visibility = View.GONE
                    ivPageNumberUp.visibility = View.VISIBLE
                    isFilteringOptionsDown = false

                } else {
                    ivPageNumberDown.visibility = View.VISIBLE
                    ivPageNumberUp.visibility = View.GONE
                    isFilteringOptionsDown = true
                }
            }

            override fun onButtonOkeyClicked(dialog: Dialog) {
                dialog.dismiss()
            }

        })
    }

    private fun checkFields(note: String, pageNumber: String): Boolean{
        if(note.isNullOrBlank()){
            Toast.makeText(requireContext(),R.string.warning_note, Toast.LENGTH_SHORT).show()
            return false
        }
        if(pageNumber.isNullOrEmpty()){
            Toast.makeText(requireContext(),R.string.warning_note_page_number, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


}