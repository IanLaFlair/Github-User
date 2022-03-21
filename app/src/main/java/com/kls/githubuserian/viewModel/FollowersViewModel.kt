package com.kls.githubuserian.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val userList = MutableLiveData<ArrayList<UserModel>>()

    fun getListFollower(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollower(username)
        client.enqueue(object: Callback<ArrayList<UserModel>>{
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    userList.value = response.body()
                } else {
                    Log.e("APIERROR", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                _isLoading.value = false
                Log.e("FAILURE", "onFailure: ${t.message.toString()}")
            }

        })
    }

}