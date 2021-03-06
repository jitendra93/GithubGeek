package com.jitendraalekar.githubgeek.data.model

import com.google.gson.annotations.SerializedName


data class RepositorySearchResponse (

	@SerializedName("total_count") val totalCount : Int,
	@SerializedName("incomplete_results") val incompleteResults : Boolean,
	@SerializedName("items") val githubRepositories : List<GithubRepository>
)