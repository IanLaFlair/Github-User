package com.kls.githubuserian.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "FavoriteUser")
@Parcelize
data class UserDatabase (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "username")
    var username: String? = null,

    @ColumnInfo(name = "avatar")
    var avatar: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "location")
    var location: String? = null,

    @ColumnInfo(name = "company")
    var company: String? = null,

    @ColumnInfo(name = "following")
    var following: Int? = 0,

    @ColumnInfo(name = "followers")
    var followers: Int? = 0,

    @ColumnInfo(name = "public_repos")
    var repository: Int? = 0,

): Parcelable








