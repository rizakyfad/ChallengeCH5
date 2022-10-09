package com.rizaki.challengech5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)

abstract class UserDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(User::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "userdb.db"
                    ).build()
                }

            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}