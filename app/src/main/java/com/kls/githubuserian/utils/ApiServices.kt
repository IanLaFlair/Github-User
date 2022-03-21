package com.kls.githubuserian.utils

import com.kls.githubuserian.model.UserDetailResponse
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("search/users")
    fun getSearchUser(@Query("q")users: String): Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username")username: String) : Call<UserDetailResponse>

    @GET("users/{username}/followers")
    fun getUserFollower(@Path("username")username: String) : Call<ArrayList<UserModel>>

    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username")username: String) : Call<ArrayList<UserModel>>

}