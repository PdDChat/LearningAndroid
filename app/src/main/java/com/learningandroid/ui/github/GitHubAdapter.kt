package com.learningandroid.ui.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningandroid.databinding.ListGithubRepositoriesBinding
import com.learningandroid.databinding.ListGithubRepositoriesHeaderBinding
import com.learningandroid.model.data.Repositories

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repositories>() {
    override fun areItemsTheSame(oldItem: Repositories, newItem: Repositories): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Repositories, newItem: Repositories): Boolean = oldItem == newItem
}

class GitHubAdapter: ListAdapter<Repositories, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_REPOSITORY_LISt = 1
    }

    class GithubRepositoriesHeaderViewHolder(binding: ListGithubRepositoriesHeaderBinding): RecyclerView.ViewHolder(binding.root)

    class GithubRepositoriesViewHolder(private val binding: ListGithubRepositoriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repositories: Repositories) {
            binding.repositoryName.text = repositories.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ListGithubRepositoriesHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GithubRepositoriesHeaderViewHolder(binding)
            }
            else -> {
                val binding = ListGithubRepositoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GithubRepositoriesViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GithubRepositoriesViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_REPOSITORY_LISt
        }
    }
}