package com.jitendraalekar.githubgeek.domain

import com.jitendraalekar.githubgeek.data.GithubService
import com.jitendraalekar.githubgeek.data.IRepository
import com.jitendraalekar.githubgeek.data.model.Contributor
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class FetchContributorsUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: IRepository
) : UseCase<String, List<Contributor>>(
    coroutineDispatcher
) {
    override suspend fun execute(parameters: String): List<Contributor> {
        return repository.fetchContributors(parameters).take(3)
    }

}