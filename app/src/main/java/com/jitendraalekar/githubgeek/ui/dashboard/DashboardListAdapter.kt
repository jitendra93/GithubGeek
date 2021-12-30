package com.jitendraalekar.githubgeek.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import com.jitendraalekar.githubgeek.databinding.RepoListItemBinding


class DashboardListAdapter(
    private val onRowClick: (repo : GithubRepository) -> Unit,
) : ListAdapter<GithubRepository, DashboardListViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListViewHolder {
        return DashboardListViewHolder(
            RepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DashboardListViewHolder, position: Int) {
        holder.bind(getItem(position), onRowClick)
    }

    private object DiffCallBack : DiffUtil.ItemCallback<GithubRepository>() {
        override fun areItemsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
            return oldItem.id == newItem.id
        }
    }
}