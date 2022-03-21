package com.kls.githubuserian.viewModel

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kls.githubuserian.database.UserDatabase
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.utils.UserRepository

class UserFavoriteViewModel(application: Application): ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)
    fun getAllNotes(): LiveData<List<UserDatabase>> = mUserRepository.getAllUser()
}