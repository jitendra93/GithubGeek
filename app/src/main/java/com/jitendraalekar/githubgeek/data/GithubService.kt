package com.jitendraalekar.githubgeek.data

import com.jitendraalekar.githubgeek.data.model.Contributor
import com.jitendraalekar.githubgeek.data.model.Issue
import com.jitendraalekar.githubgeek.data.model.RepositorySearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    suspend fun fetchRepositories(@Query("q") param : String) : RepositorySearchResponse

    @GET("repos/{fullName}/issues")
    suspend fun fetchIssues(@Path("fullName",encoded = true) param : String) : List<Issue>

    @GET("repos/{fullName}/contributors")
    suspend fun fetchContributors(@Path("fullName",encoded = true) param : String) : List<Contributor>


}