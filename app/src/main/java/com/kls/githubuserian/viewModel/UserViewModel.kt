package com.kls.githubuserian.viewModel

import androidx.lifecycle.*
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.model.UserResponse
import com.kls.githubuserian.utils.ApiConfig
import com.kls.githubuserian.utils.SettingPreferences
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val pref: SettingPreferences?): ViewModel() {

    val userList = MutableLiveData<ArrayList<UserModel>?>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        setListUser("sidiqpermana")
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref?.getThemeSetting()!!.asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref?.saveThemeSetting(isDarkModeActive)
        }
    }

    fun setListUser(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(username)
        client.enqueue(object: Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    userList.value = response.body()?.getList
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}