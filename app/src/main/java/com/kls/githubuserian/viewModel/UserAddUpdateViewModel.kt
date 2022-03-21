package com.kls.githubuserian.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kls.githubuserian.database.UserDatabase
import com.kls.githubuserian.utils.UserRepository

class UserAddUpdateViewModel(application: Application): ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)
    fun insert(user: UserDatabase) {
        mUserRepository.insert(user)
    }
    fun update(user: UserDatabase) {
        mUserRepository.update(user)
    }
    fun delete(user: UserDatabase) {
        mUserRepository.delete(user)
    }
}