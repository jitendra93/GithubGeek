package com.jitendraalekar.githubgeek.data.model

import com.google.gson.annotations.SerializedName


data class User (

	@SerializedName("login") val login : String,
	@SerializedName("id") val id : Int,
	@SerializedName("avatar_url") val avatar_url : String,

)