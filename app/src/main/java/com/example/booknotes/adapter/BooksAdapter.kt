package com.example.booknotes.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.booknotes.databinding.CardBookBinding
import com.example.booknotes.model.Book
import com.example.booknotes.view.BooksFragmentDirections

class BooksAdapter(val context: Context, var bookList: List<Book>) : RecyclerView.Adapter<BooksAdapter.booksViewHolder>() {

    class booksViewHolder(val binding: CardBookBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): booksViewHolder {
        return booksViewHolder(
            CardBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: BooksAdapter.booksViewHolder, position: Int) {
        val data = bookList[position]
        Log.d("aaa",data.bookColor!!)
        if(data.bookColor == "#393E46"){
            holder.binding.bookName.setTextColor(Color.parseColor("#FFFFFF"))
            holder.binding.bookAuthor.setTextColor(Color.parseColor("#FFFFFF"))
            holder.binding.bookGenre.setTextColor(Color.parseColor("#FFFFFF"))
        }
        holder.binding.bookName.text = data.bookName
        holder.binding.bookAuthor.text = data.bookAuthor
        holder.binding.bookGenre.text = data.bookGenre
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