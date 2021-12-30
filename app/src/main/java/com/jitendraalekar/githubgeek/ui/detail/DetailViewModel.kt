package com.jitendraalekar.githubgeek.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jitendraalekar.githubgeek.util.Result
import com.jitendraalekar.githubgeek.data.model.Contributor
import com.jitendraalekar.githubgeek.data.model.Issue
import com.jitendraalekar.githubgeek.domain.FetchContributorsUseCase
import com.jitendraalekar.githubgeek.domain.FetchIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchIssuesUseCase: FetchIssuesUseCase,
    private val fetchContributorsUseCase: FetchContributorsUseCase
) : ViewModel() {

    private val _issues: MutableLiveData<Result<List<Issue>>> = MutableLiveData(Result.Loading)
    val issues: LiveData<Result<List<Issue>>> by ::_issues

    private val _contributors: MutableLiveData<Result<List<Contributor>>> =
        MutableLiveData(Result.Loading)
    val contributor: LiveData<Result<List<Contributor>>> by ::_contributors


    fun load(repoFullName: String) {

        viewModelScope.launch {
            val contributors = async { fetchContributorsUseCase.invoke(repoFullName) }
            val issues = async { fetchIssuesUseCase.invoke(repoFullName) }
            _contributors.value = contributors.await()
            _issues.postValue(issues.await())
        }
    }

}