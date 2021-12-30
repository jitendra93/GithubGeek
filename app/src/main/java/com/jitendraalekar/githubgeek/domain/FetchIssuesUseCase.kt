package com.jitendraalekar.githubgeek.domain

import com.jitendraalekar.githubgeek.data.GithubService
import com.jitendraalekar.githubgeek.data.IRepository
import com.jitendraalekar.githubgeek.data.model.Issue
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class FetchIssuesUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: IRepository
) : UseCase<String, List<Issue>>(
    coroutineDispatcher
) {
    override suspend fun execute(parameters: String): List<Issue> {
        return repository.fetchIssues(parameters).take(3)
    }

}