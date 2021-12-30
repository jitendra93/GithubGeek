package com.jitendraalekar.githubgeek.domain

import android.util.Log
import com.jitendraalekar.githubgeek.data.GithubService
import com.jitendraalekar.githubgeek.data.IRepository
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class FetchRepositoriesUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    val repository: IRepository
) : UseCase<String, List<GithubRepository>>(
    coroutineDispatcher
) {
    override suspend fun execute(parameters: String): List<GithubRepository> {
        val repositorySearchResponse = repository.fetchRepos(parameters)
        Log.d("Jitendra",repositorySearchResponse.toString())
        return repositorySearchResponse.githubRepositories
    }

}