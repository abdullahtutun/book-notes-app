package com.example.booknotes.view

import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booknotes.Constant
import com.example.booknotes.Constant.SHARED_KEY_ACTIVE_SORTING
import com.example.booknotes.R
import com.example.booknotes.SessionManager
import com.example.booknotes.adapter.NotesAdapter
import com.example.booknotes.databinding.BottomSheetLayoutNotesBinding
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.helper.DialogHelper
import com.example.booknotes.helper.SharedPreferencesHelper
import com.example.booknotes.model.Note
import com.example.booknotes.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment() {
    @Inject
    lateinit var sessionManager: SessionManager
    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val bookOfNotes by navArgs<NotesFragmentArgs>()
    private lateinit var binding: FragmentNotesBinding
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater, container,false)

        sessionManager.getNoteOptionsFromSharedPreferences()
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

    private fun getNotes() {
        val noteList: List<Note>

        if(sessionManager.noteSortingOptions.filteringFavorite) {
            val list = getNotesByIsFavorite(true)

            noteList = if(sharedPreferencesHelper.getInt(SHARED_KEY_ACTIVE_SORTING, 0) == 0){
                getNotesByDateCreated(list)

            } else {
                getNotesByPageNo(list)
            }

        } else {
            val list = getNotesByIsFavorite(false)

            noteList = if(sharedPreferencesHelper.getInt(SHARED_KEY_ACTIVE_SORTING, 0) == 0){
                getNotesByDateCreated(list)

            } else {
                getNotesByPageNo(list)
            }
        }
        adapter = NotesAdapter((requireContext()), noteList, binding)
        binding.rvNotes.adapter = adapter
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
        getNotes()
    }

    private fun onStar(v: View){
        adapter.setStarOfNote(viewModel)
        getNotes()
    }

    private fun onFabNotes(v: View){
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: String = sdf.format(Date())

        DialogHelper.addNoteDialog(requireContext(), object : DialogHelper.MessageDialogListener {
            override fun onPositiveButtonClicked(dialog:Dialog, note: TextView, pageNumber: TextView) {
                val note = note.text.toString()
                val pageNumber = pageNumber.text.toString()
                val book = bookOfNotes.data

                if(checkFields(note, pageNumber)){
                    val noteObj = Note(null, note, pageNumber.toInt(), book, 0, date)
                    viewModel.addNote(noteObj)
                    getNotes()
                    adapter.clearSelectedList()
                    dialog.dismiss()
                }
            }
            override fun onNegativeButtonClicked() {

            }
        })
    }

    private fun showBottomSheetDialog() {
        var isOpenSortingOptions = false
        var isOpenFilteringOptions = false
        var isDateCreatedOptionDown = sessionManager.noteSortingOptions.isDateCreatedDown
        var isPageNumberOptionDown = sessionManager.noteSortingOptions.isPageNumberDown
        var cbFilterFavorite = sessionManager.noteSortingOptions.filteringFavorite

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_notes)

        val llSorting = dialog.findViewById(R.id.llSorting) as LinearLayout
        val llFiltering = dialog.findViewById(R.id.llFiltering) as LinearLayout
        val llSortingOptions = dialog.findViewById(R.id.llSortingOptions) as LinearLayout
        val llFilteringOptions = dialog.findViewById(R.id.llFilteringOptions) as LinearLayout
        val llDateCreated = dialog.findViewById(R.id.llDateCreated) as LinearLayout
        val llPageNumber = dialog.findViewById(R.id.llPageNumber) as LinearLayout
        val ivPageNumber = dialog.findViewById(R.id.ivPageNumber) as ImageView
        val ivDateCreated = dialog.findViewById(R.id.ivDateCreated) as ImageView
        val cbFavorite = dialog.findViewById(R.id.cbFavorite) as CheckBox
        val buttonOk = dialog.findViewById(R.id.buttonOkey) as Button

        Log.d("abdullah", sharedPreferencesHelper.getBoolean(Constant.SHARED_KEY_DATE_CREATED, true).toString())
        Log.d("abdullah", sharedPreferencesHelper.getBoolean(Constant.SHARED_KEY_PAGE_NUMBER, true).toString())
        Log.d("abdullah", sharedPreferencesHelper.getBoolean(Constant.SHARED_KEY_FILTERING_FAVORITE, false).toString())

        if(sessionManager.noteSortingOptions.isDateCreatedDown){
            if(sharedPreferencesHelper.getInt(SHARED_KEY_ACTIVE_SORTING, 0) == 0){
                ivDateCreated.setImageResource(R.drawable.ic_sort_down_black)
            } else {
                ivDateCreated.setImageResource(R.drawable.ic_sort_down)
            }

        } else {
            if(sharedPreferencesHelper.getInt(SHARED_KEY_ACTIVE_SORTING, 0) == 0){
                ivDateCreated.setImageResource(R.drawable.ic_sort_up_black)
            } else {
                ivDateCreated.setImageResource(R.drawable.ic_sort_up)
            }
        }

        if(sessionManager.noteSortingOptions.isPageNumberDown){
            if(sharedPreferencesHelper.getInt(SHARED_KEY_ACTIVE_SORTING, 0) == 1){
                ivPageNumber.setImageResource(R.drawable.ic_sort_down_black)
            } else {
                ivPageNumber.setImageResource(R.drawable.ic_sort_down)
            }

        } else {
            if(sharedPreferencesHelper.getInt(SHARED_KEY_ACTIVE_SORTING, 0) == 1){
                ivPageNumber.setImageResource(R.drawable.ic_sort_up_black)
            } else {
                ivPageNumber.setImageResource(R.drawable.ic_sort_up)
            }
        }

        cbFavorite.isChecked = sessionManager.noteSortingOptions.filteringFavorite

        llSorting.setOnClickListener{
            if(!isOpenSortingOptions){
                llSortingOptions.visibility = View.VISIBLE
                isOpenSortingOptions = true

            } else {
                llSortingOptions.visibility = View.GONE
                isOpenSortingOptions = false
            }
        }

        llFiltering.setOnClickListener{
            if(!isOpenFilteringOptions){
                llFilteringOptions.visibility = View.VISIBLE
                isOpenFilteringOptions = true

            } else {
                llFilteringOptions.visibility = View.GONE
                isOpenFilteringOptions = false
            }
        }

        cbFavorite.setOnCheckedChangeListener { _, isChecked ->
            cbFilterFavorite = isChecked
        }

        llDateCreated.setOnClickListener {
            isDateCreatedOptionDown = if(isDateCreatedOptionDown){
                ivDateCreated.setImageResource(R.drawable.ic_sort_up_black)
                false

            } else {
                ivDateCreated.setImageResource(R.drawable.ic_sort_down_black)
                true
            }

            if(isPageNumberOptionDown) {
                ivPageNumber.setImageResource(R.drawable.ic_sort_down)

            } else {
                ivPageNumber.setImageResource(R.drawable.ic_sort_up)
            }
            sharedPreferencesHelper.putInt(SHARED_KEY_ACTIVE_SORTING, 0)
        }

        llPageNumber.setOnClickListener {
            isPageNumberOptionDown = if(isPageNumberOptionDown){
                ivPageNumber.setImageResource(R.drawable.ic_sort_up_black)
                false

            } else {
                ivPageNumber.setImageResource(R.drawable.ic_sort_down_black)
                true
            }

            if(isDateCreatedOptionDown) {
                ivDateCreated.setImageResource(R.drawable.ic_sort_down)
            } else {
                ivDateCreated.setImageResource(R.drawable.ic_sort_up)
            }
            sharedPreferencesHelper.putInt(SHARED_KEY_ACTIVE_SORTING, 1)
        }

        buttonOk.setOnClickListener {
            sessionManager.noteSortingOptions.isDateCreatedDown = isDateCreatedOptionDown
            sessionManager.noteSortingOptions.isPageNumberDown = isPageNumberOptionDown
            sessionManager.noteSortingOptions.filteringFavorite = cbFilterFavorite
            sessionManager.saveNoteOptionsToSharedPreferences()

            getNotes()

            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun checkFields(note: String, pageNumber: String): Boolean{
        if(note.isBlank()){
            Toast.makeText(requireContext(), R.string.warning_note, Toast.LENGTH_SHORT).show()
            return false
        }
        if(pageNumber.isEmpty()){
            Toast.makeText(requireContext(), R.string.warning_note_page_number, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun getNotesByIsFavorite(isFavorite: Boolean): List<Note> {
        var list: List<Note> = if (isFavorite) {
            viewModel.getNotesByFavorite(bookOfNotes.data)

        } else {
            viewModel.getNotes(bookOfNotes.data)
        }

        return list
    }

    private fun getNotesByDateCreated(list: List<Note>): List<Note> {
        return if(sessionManager.noteSortingOptions.isDateCreatedDown){
            list.sortedByDescending { it.createdDate }

        } else {
            list.sortedBy { it.createdDate}
        }
    }

    private fun getNotesByPageNo(list: List<Note>) : List<Note>{
        return if(sessionManager.noteSortingOptions.isPageNumberDown){
            list.sortedByDescending { it.pageNo }

        } else {
            list.sortedBy { it.pageNo}
        }
    }
}