package com.example.booknotes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booknotes.R
import com.example.booknotes.databinding.CardNoteBinding
import com.example.booknotes.databinding.FragmentNotesBinding
import com.example.booknotes.model.Note
import com.example.booknotes.util.copyToClipboard
import com.example.booknotes.viewModel.NotesViewModel

class NotesAdapter(val context: Context, var noteList: List<Note>, private val bindingNotesFragment: FragmentNotesBinding) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var isEnable: Boolean = false
    private var itemSelectedList = mutableListOf<Note?>()
    private var isMaxLines: Boolean = false
    private lateinit var myHolder: NotesAdapter.NotesViewHolder

    class NotesViewHolder(val binding: CardNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            DataBindingUtil.inflate<CardNoteBinding>(inflater, R.layout.card_note,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        myHolder = holder
        val data = noteList[position]
        holder.binding.note = data

        if (itemSelectedList.isEmpty()){
            holder.binding.cardNote.setCardBackgroundColor(Color.WHITE)
        }

        holder.binding.root.setOnClickListener{
            clickedCard(holder, data)
        }

        holder.binding.root.setOnLongClickListener {
            selectItem(holder, data)
            true
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
    
    private fun clickedCard(holder: NotesAdapter.NotesViewHolder, item: Note){
        if(itemSelectedList.contains(item)){
            itemSelectedList.removeAt(itemSelectedList.indexOf(item))
            holder.binding.cardNote.setCardBackgroundColor(Color.WHITE)
            bindingNotesFragment.toolbarNotes.tvLongClickedItemCount.text = itemSelectedList.size.toString()
            if (itemSelectedList.isEmpty()){
                setToolbar(false)
                isEnable = false
             } else if(itemSelectedList.size == 1){
                 bindingNotesFragment.toolbarNotes.ivCopy.visibility = View.VISIBLE
             }

        } else if(isEnable){
            selectItem(holder,item)

        } else {
            if(!isMaxLines){
                holder.binding.tvNote.maxLines = Int.MAX_VALUE
                isMaxLines = true
            } else {
                holder.binding.tvNote.maxLines = 5
                isMaxLines = false
            }
        }
    }

    private fun selectItem(holder: NotesAdapter.NotesViewHolder, item: Note){
        if(!itemSelectedList.contains(item)){
            isEnable = true
            itemSelectedList.add(item)
            holder.binding.cardNote.setCardBackgroundColor(Color.parseColor("#dcdcdc"))
            bindingNotesFragment.toolbarNotes.tvLongClickedItemCount.text = itemSelectedList.size.toString()
            setToolbar(true)
        }

    }

    fun setStarOfNote(vm: NotesViewModel){
        if(itemSelectedList.isNotEmpty()) {
            itemSelectedList.forEach {
                if (it!!.isFavorite == 0){
                    val note = Note(it.id, it.note, it.pageNo, it.bookName,1, it.createdDate)
                    vm.updateNote(note)

                } else{
                    val note = Note(it.id, it.note, it.pageNo, it.bookName,0, it.createdDate)
                    vm.updateNote(note)
                }
                myHolder.binding.ivNoteStar.visibility = View.VISIBLE
            }
            clearSelectedList()
        }
    }

    fun copyNote(){
        if(itemSelectedList.size == 1){
            itemSelectedList[0]?.note?.copyToClipboard(context)
            clearSelectedList()

            Toast.makeText(context,R.string.coppied_note, Toast.LENGTH_LONG).show()
        }
    }

    fun deleteNote(vm: NotesViewModel){
        if(itemSelectedList.isNotEmpty()) {
            itemSelectedList.forEach {
                vm.deleteNote(it!!.id!!)
            }
            clearSelectedList()
        }
    }

    fun clearSelectedList(){
        isEnable = false
        itemSelectedList.clear()
        setToolbar(false)
        notifyDataSetChanged()
    }

    private fun setToolbar(isLongClicked: Boolean){
        if(isLongClicked){
            bindingNotesFragment.toolbarNotes.toolbarLongClicked.visibility = View.VISIBLE
            bindingNotesFragment.toolbarNotes.ivFilter.visibility = View.GONE
            bindingNotesFragment.toolbarNotes.ivBack.visibility = View.GONE

        } else {
            bindingNotesFragment.toolbarNotes.toolbarLongClicked.visibility = View.GONE
            bindingNotesFragment.toolbarNotes.ivFilter.visibility = View.VISIBLE
            bindingNotesFragment.toolbarNotes.ivBack.visibility = View.VISIBLE
        }

        if(itemSelectedList.size == 1){
            bindingNotesFragment.toolbarNotes.ivCopy.visibility = View.VISIBLE

        } else {
            bindingNotesFragment.toolbarNotes.ivCopy.visibility = View.GONE
        }
    }



}