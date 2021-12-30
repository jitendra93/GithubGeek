package com.jitendraalekar.githubgeek.data.model
import com.google.gson.annotations.SerializedName



data class GithubRepository (

	@SerializedName("id") val id : Int,
	@SerializedName("node_id") val node_id : String,
	@SerializedName("name") val name : String,
	@SerializedName("full_name") val full_name : String,
	@SerializedName("private") val private : Boolean,
	@SerializedName("owner") val owner : Owner,
	@SerializedName("html_url") val html_url : String,
	@SerializedName("description") val description : String,
	@SerializedName("fork") val fork : Boolean,
	@SerializedName("url") val url : String
)