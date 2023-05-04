package com.learningandroid.ui.roulette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningandroid.databinding.ListRouletteBinding
import com.learningandroid.model.data.RouletteInfo

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RouletteInfo>() {
    override fun areItemsTheSame(oldItem: RouletteInfo, newItem: RouletteInfo): Boolean = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: RouletteInfo, newItem: RouletteInfo): Boolean = oldItem == newItem
}

class RouletteAdapter: ListAdapter<RouletteInfo, RouletteAdapter.RouletteListViewHolder>(DIFF_CALLBACK) {

    class RouletteListViewHolder(private val binding: ListRouletteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(rouletteInfo: RouletteInfo) {
            binding.rouletteListName.text = rouletteInfo.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouletteListViewHolder {
        val binding = ListRouletteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RouletteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouletteListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}