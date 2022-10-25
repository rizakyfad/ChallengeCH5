package com.rizaki.challengech6Binar.database.local

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    fun checkUser(email: String, password: String): User

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): User
}