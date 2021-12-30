package com.jitendraalekar.githubgeek.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.githubgeek.data.model.Issue
import com.jitendraalekar.githubgeek.databinding.ListItemIssuesBinding


class IssueListViewHolder(val binding: ListItemIssuesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        issue: Issue,

    ) {
        binding.apply {
            binding.issueItem = ListItem(issueNumber = "#${issue.number}",
            userAvatar = issue.user.avatar_url,
            title = issue.title,
            login = issue.user.login)
        }

    }
}

data class ListItem(val issueNumber : String, val userAvatar : String, val title : String,val login : String)