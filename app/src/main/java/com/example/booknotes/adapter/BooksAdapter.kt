package com.example.booknotes.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.booknotes.R
import com.example.booknotes.databinding.CardBookBinding
import com.example.booknotes.databinding.FragmentBooksBinding
import com.example.booknotes.model.Book
import com.example.booknotes.view.BooksFragmentDirections
import com.example.booknotes.viewModel.BooksViewModel

class BooksAdapter(var bookList: List<Book>, private val bindingBooksFragment: FragmentBooksBinding) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(){
    private var isEnable: Boolean = false
    private var itemSelectedList = mutableListOf<Book?>()

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

        if (itemSelectedList.isEmpty()){
            holder.binding.clCard.setBackgroundColor(data.bookColor!!)
        }

        holder.binding.root.setOnClickListener{
            clickedCard(holder, data, it)
        }

        holder.binding.root.setOnLongClickListener {
            selectItem(holder, data)
            true
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }
    private fun clickedCard(holder: BooksAdapter.BooksViewHolder, data: Book, v: View){
        if(itemSelectedList.contains(data)){
            itemSelectedList.removeAt(itemSelectedList.indexOf(data))
            holder.binding.clCard.setBackgroundColor(data.bookColor!!)
            bindingBooksFragment.toolbarBooks.tvLongClickedItemCount.text = itemSelectedList.size.toString()
            if (itemSelectedList.isEmpty()){
                setToolbar(false)
                isEnable = false
            } else if(itemSelectedList.size == 1){
                bindingBooksFragment.toolbarBooks.ivExport.visibility = View.VISIBLE
            }

        } else if(isEnable){
            selectItem(holder,data)

        } else {
            val action = BooksFragmentDirections.actionBooksFragmentToNotesFragment(data.bookName!!)
            Navigation.findNavController(v).navigate(action)
        }
    }

    fun deleteBook(vm: BooksViewModel){
        if(itemSelectedList.isNotEmpty()) {
            itemSelectedList.forEach {
                vm.deleteBook(it!!.id!!)
            }
            clearSelectedList()
        }
    }

    private fun selectItem(holder: BooksAdapter.BooksViewHolder, item: Book){
        if(!itemSelectedList.contains(item)){
            isEnable = true
            itemSelectedList.add(item)
            holder.binding.clCard.setBackgroundColor(Color.parseColor("#dcdcdc"))
            bindingBooksFragment.toolbarBooks.tvLongClickedItemCount.text = itemSelectedList.size.toString()
            setToolbar(true)
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
            bindingBooksFragment.toolbarBooks.toolbarLongClicked.visibility = View.VISIBLE
            bindingBooksFragment.toolbarBooks.ivFilter.visibility = View.GONE
            bindingBooksFragment.toolbarBooks.ivOptions.visibility = View.GONE

        } else {
            bindingBooksFragment.toolbarBooks.toolbarLongClicked.visibility = View.GONE
            bindingBooksFragment.toolbarBooks.ivFilter.visibility = View.VISIBLE
            bindingBooksFragment.toolbarBooks.ivOptions.visibility = View.VISIBLE
        }

        if(itemSelectedList.size == 1){
            bindingBooksFragment.toolbarBooks.ivExport.visibility = View.VISIBLE

        } else {
            bindingBooksFragment.toolbarBooks.ivExport.visibility = View.GONE
        }
    }

}