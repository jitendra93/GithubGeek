package com.jitendraalekar.githubgeek.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.githubgeek.data.model.Contributor
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import com.jitendraalekar.githubgeek.databinding.ListItemContributorBinding
import com.jitendraalekar.githubgeek.databinding.RepoListItemBinding


class ContributorsListViewHolder(val binding: ListItemContributorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        contributor: Contributor

    ) {
        binding.apply {
            binding.contributor = contributor
        }

    }
}