package com.learningandroid.ui.roulette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningandroid.databinding.ListRouletteBinding
import com.learningandroid.model.data.RouletteInfo

class RouletteAdapter: ListAdapter<RouletteInfo, RouletteAdapter.RouletteListViewHolder>(DIFF_CALLBACK) {

    class RouletteListViewHolder(binding: ListRouletteBinding): RecyclerView.ViewHolder(binding.root) {
        val name = binding.rouletteListName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouletteListViewHolder {
        val binding = ListRouletteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RouletteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouletteListViewHolder, position: Int) {
        val item = getItem(position)
        holder.name.text = item.name
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RouletteInfo>() {
    override fun areItemsTheSame(oldItem: RouletteInfo, newItem: RouletteInfo): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: RouletteInfo, newItem: RouletteInfo): Boolean = oldItem == newItem
}