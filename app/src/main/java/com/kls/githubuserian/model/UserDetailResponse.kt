package com.kls.githubuserian.model

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("login")
    val username: String? = null,

    @field:SerializedName("avatar_url")
    val avatar: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("public_repos")
    val repository: Int? = null,

)
