package com.learningandroid.ui.googlebook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learningandroid.databinding.ListBookInfoBinding
import com.learningandroid.model.data.Items

class BookAdapter(private val fragment: Fragment): ListAdapter<Items, BookAdapter.BookViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean = oldItem == newItem
        }
    }

    inner class BookViewHolder(private val binding: ListBookInfoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.bookName.text = item.volumeInfo?.title
            Glide.with(fragment)
                .load(item.volumeInfo?.imageLinks?.thumbnail)
                .into(binding.bookIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ListBookInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}