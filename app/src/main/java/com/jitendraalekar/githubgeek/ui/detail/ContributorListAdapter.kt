package com.jitendraalekar.githubgeek.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jitendraalekar.githubgeek.data.model.Contributor
import com.jitendraalekar.githubgeek.databinding.ListItemContributorBinding


class ContributorListAdapter : ListAdapter<Contributor, ContributorsListViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorsListViewHolder {
        return ContributorsListViewHolder(
            ListItemContributorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContributorsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffCallBack : DiffUtil.ItemCallback<Contributor>() {
        override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor): Boolean {
            return oldItem.id == newItem.id
        }
    }
}