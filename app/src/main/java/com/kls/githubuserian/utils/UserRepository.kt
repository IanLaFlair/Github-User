package com.kls.githubuserian.utils

import android.app.Application
import androidx.lifecycle.LiveData
import com.kls.githubuserian.database.UserDao
import com.kls.githubuserian.database.UserDatabase
import com.kls.githubuserian.database.UserRoomDatabase
import com.kls.githubuserian.model.UserModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val mUserDao: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUserDao = db.userDao()
    }

    fun getAllUser(): LiveData<List<UserDatabase>> = mUserDao.getAllUser()
    fun insert(user: UserDatabase) {
        executorService.execute { mUserDao.insert(user) }
    }
    fun delete(user: UserDatabase) {
        executorService.execute { mUserDao.delete(user) }
    }
    fun update(user: UserDatabase) {
        executorService.execute { mUserDao.update(user) }
    }

}