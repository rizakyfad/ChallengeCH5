package com.rizaki.challengech5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


class UserRepository(private val userDao: UserDao) {
    fun insert(user: User) {
        userDao.insert(user)
    }

    fun checkUser(email: String, password: String) {
        userDao.checkUser(email, password)
    }
}