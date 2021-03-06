package com.kls.githubuserian.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val username: String? = null,

    @field:SerializedName("avatar_url")
    val avatar: String? = null,

    @field:SerializedName("html_url")
    val url_github:String? =null

): Parcelable