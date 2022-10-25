package com.rizaki.challengech6Binar.database.local

class UserRepository(private val userDao: UserDao) {

    fun insert(user: User) {
        userDao.insert(user)
    }

    fun update(user: User) {
        userDao.update(user)
    }

    fun checkUser(email: String, password: String): User {
        return userDao.checkUser(email, password)
    }

    fun getUser(id: Int): User {
        return userDao.getUser(id)
    }
}