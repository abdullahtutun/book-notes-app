package com.example.booknotes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booknotes.R
import com.example.booknotes.databinding.CardNoteBinding
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.model.Note
import com.example.booknotes.viewModel.NotesViewModel

class NotesAdapter(val context: Context, var noteList: List<Note>, private val bindingNotesFragment: FragmentNotesBinding) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var isEnable: Boolean = false
    private var itemSelectedList = mutableListOf<Int?>()

    class NotesViewHolder(val binding: CardNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            DataBindingUtil.inflate<CardNoteBinding>(inflater, R.layout.card_note,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        val item = noteList[position]
        holder.binding.note = item

        holder.binding.root.setOnClickListener{
            if(itemSelectedList.contains(item.id!!)){
                itemSelectedList.remove(item.id!!)
                holder.binding.cardNote.setCardBackgroundColor(Color.WHITE)
                if (itemSelectedList.isEmpty()){
                    bindingNotesFragment.toolbarNotes.llDeleteCopy.visibility = View.GONE
                    bindingNotesFragment.toolbarNotes.ivFilter.visibility = View.VISIBLE
                    bindingNotesFragment.toolbarNotes.ivBack.visibility = View.VISIBLE
                    isEnable = false

                }
            } else if(isEnable){
                selectItem(holder,item.id!!)
            }
        }

        holder.binding.root.setOnLongClickListener {
            selectItem(holder, item.id!!)
            true
        }

    }

    private fun selectItem(holder: NotesAdapter.NotesViewHolder,itemId: Int){
        isEnable = true
        itemSelectedList.add(itemId!!)
        holder.binding.cardNote.setCardBackgroundColor(Color.parseColor("#dcdcdc"))
        bindingNotesFragment.toolbarNotes.llDeleteCopy.visibility = View.VISIBLE
        bindingNotesFragment.toolbarNotes.ivFilter.visibility = View.GONE
        bindingNotesFragment.toolbarNotes.ivBack.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun deleteNote(vm: NotesViewModel){
        if(itemSelectedList.isNotEmpty()) {
            itemSelectedList.forEach {
                vm.deleteNote(it!!)
            }
            isEnable = false
            itemSelectedList.clear()
            bindingNotesFragment.toolbarNotes.llDeleteCopy.visibility = View.GONE
            bindingNotesFragment.toolbarNotes.ivFilter.visibility = View.VISIBLE
            bindingNotesFragment.toolbarNotes.ivBack.visibility = View.VISIBLE
        }
        notifyDataSetChanged()
    }


}