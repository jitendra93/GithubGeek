package com.jitendraalekar.githubgeek.ui.dashboard

import com.jitendraalekar.githubgeek.data.model.GithubRepository

sealed class DashboardViewState {
    object Loading : DashboardViewState()
    data class Content(val list: List<GithubRepository>) :
        DashboardViewState()

    data class Error(val throwable: Throwable) : DashboardViewState()
}