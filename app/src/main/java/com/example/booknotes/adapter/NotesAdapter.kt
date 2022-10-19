package com.example.booknotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booknotes.databinding.CardNoteBinding
import com.example.booknotes.helpers.MessageDialogHelper
import com.example.booknotes.model.Note

class NotesAdapter(val context: Context, var noteList: List<Note>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    class notesViewHolder(val binding: CardNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            CardNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter.notesViewHolder, position: Int) {
        val data = noteList[position]

        holder.binding.note.text = data.note

        holder.binding.root.setOnClickListener{
           // MessageDialogHelper.noteInfoDialog(context, data.bookName, data.pageNo)
        }

    }

    override fun getItemCount(): Int {
        return noteList.size
    }



}