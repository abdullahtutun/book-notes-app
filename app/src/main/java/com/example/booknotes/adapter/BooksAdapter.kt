package com.example.booknotes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.booknotes.R
import com.example.booknotes.databinding.CardBookBinding
import com.example.booknotes.model.Book
import com.example.booknotes.view.BooksFragmentDirections

class BooksAdapter(var bookList: List<Book>) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(){

    class BooksViewHolder(val binding: CardBookBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BooksViewHolder(
            DataBindingUtil.inflate<CardBookBinding>(inflater, R.layout.card_book,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BooksAdapter.BooksViewHolder, position: Int) {
        val data = bookList[position]
        holder.binding.book = data
        if(data.bookColor == "#393E46"){
            holder.binding.bookName.setTextColor(Color.parseColor("#FFFFFF"))
            holder.binding.bookAuthor.setTextColor(Color.parseColor("#FFFFFF"))
            holder.binding.bookGenre.setTextColor(Color.parseColor("#FFFFFF"))
        }

        holder.binding.clCard.setBackgroundColor(Color.parseColor(data.bookColor!!))

        holder.binding.root.setOnClickListener{
            val action = BooksFragmentDirections.actionBooksFragmentToNotesFragment(data.bookName!!)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

}