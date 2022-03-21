package com.kls.githubuserian.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kls.githubuserian.model.UserModel

    @Dao
    interface UserDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(user: UserDatabase)
        @Update
        fun update(user: UserDatabase)
        @Delete
        fun delete(user: UserDatabase)
        @Query("SELECT * from favoriteuser ORDER BY id ASC")
        fun getAllUser(): LiveData<List<UserDatabase>>
    }
