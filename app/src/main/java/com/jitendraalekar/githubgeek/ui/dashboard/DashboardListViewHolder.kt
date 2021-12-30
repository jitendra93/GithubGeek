package com.jitendraalekar.githubgeek.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import com.jitendraalekar.githubgeek.databinding.RepoListItemBinding


class DashboardListViewHolder(val binding: RepoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        repository: GithubRepository,
        onRowClick: (repository: GithubRepository ) -> Unit

    ) {
        binding.apply {
            binding.repo = repository
            root.setOnClickListener { onRowClick(repository) }

        }

    }
}