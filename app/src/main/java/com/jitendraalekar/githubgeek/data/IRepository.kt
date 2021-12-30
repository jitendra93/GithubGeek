package com.jitendraalekar.githubgeek.data

import com.jitendraalekar.githubgeek.data.model.Contributor
import com.jitendraalekar.githubgeek.data.model.Issue
import com.jitendraalekar.githubgeek.data.model.RepositorySearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface IRepository {

    suspend fun fetchRepos(query:String) : RepositorySearchResponse
    suspend fun fetchIssues (param : String) : List<Issue>

    suspend fun fetchContributors( param : String) : List<Contributor>
}

class RepositoryImpl @Inject constructor( val githubService: GithubService) : IRepository {

    override suspend fun fetchRepos(query: String): RepositorySearchResponse {
        return githubService.fetchRepositories(query)
    }

    override suspend fun fetchIssues(repoFullName: String): List<Issue> {
        return githubService.fetchIssues(repoFullName)
    }

    override suspend fun fetchContributors(repoFullName: String): List<Contributor> {
        return githubService.fetchContributors(repoFullName)
    }

}