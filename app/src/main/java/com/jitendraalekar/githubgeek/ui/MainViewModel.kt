package com.jitendraalekar.githubgeek.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jitendraalekar.githubgeek.util.Result
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import com.jitendraalekar.githubgeek.domain.FetchRepositoriesUseCase
import com.jitendraalekar.githubgeek.ui.dashboard.DashboardViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val fetchRepositoriesUseCase: FetchRepositoriesUseCase
) : ViewModel() {

    private val _repositories: MutableLiveData<DashboardViewState> = MutableLiveData(DashboardViewState.Loading)
    val repositories: LiveData<DashboardViewState> by ::_repositories

    fun fetchRepo(id: Int) : GithubRepository? {
        return when (val result = repositories.value) {
            is DashboardViewState.Content -> {
                result.list.find { it.id == id }
            }
            else -> {
                null
            }

        }
    }

    fun onSearch(query: String?) {
        if (query.isNullOrBlank())
            return
        viewModelScope.launch {
            when (val result = fetchRepositoriesUseCase(query)) {
                is Result.Success -> {
                    _repositories.postValue( DashboardViewState.Content(result.data))
                }
                is Result.Loading -> {
                    _repositories.postValue(DashboardViewState.Loading)
                }
                is Result.Error -> {

                    _repositories.postValue(DashboardViewState.Error(result.exception))
                }
            }
        }
    }


}