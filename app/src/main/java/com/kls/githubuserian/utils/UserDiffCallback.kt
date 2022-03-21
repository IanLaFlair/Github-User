package com.kls.githubuserian.utils

import androidx.recyclerview.widget.DiffUtil
import com.kls.githubuserian.database.UserDatabase

class UserDiffCallback(private val mOldUserList: List<UserDatabase>, private val mNewUserList: List<UserDatabase>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].id == mNewUserList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = mOldUserList[oldItemPosition]
        val newUser = mNewUserList[newItemPosition]
        return oldUser.id == newUser.id &&
                oldUser.username == newUser.username &&
                oldUser.avatar == newUser.avatar &&
                oldUser.name == newUser.name &&
                oldUser.location == newUser.location &&
                oldUser.company == newUser.company &&
                oldUser.following == newUser.following &&
                oldUser.followers == newUser.followers &&
                oldUser.repository == newUser.repository
    }
}