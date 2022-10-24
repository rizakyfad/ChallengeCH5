package com.rizaki.challengech5.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    /*@ColumnInfo(name = "fullName")
    var fullName: String,

    @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth: String,

    @ColumnInfo(name = "address")
    var address: String*/
) : Parcelable