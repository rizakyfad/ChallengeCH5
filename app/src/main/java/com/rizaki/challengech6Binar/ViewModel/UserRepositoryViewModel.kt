package com.rizaki.challengech6Binar.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rizaki.challengech6Binar.database.local.User
import com.rizaki.challengech6Binar.database.local.UserRepository
import com.rizaki.challengech6Binar.database.local.UserRoomDatabase
import kotlinx.coroutines.launch


class UserRepositoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }

    fun update(user: User) {
        viewModelScope.launch {
            repository.update(user)
        }
    }

    fun getUser(id: Int): User {
        return repository.getUser(id)
    }

    fun checkUser(email: String, password: String): User {
        return repository.checkUser(email, password)
    }
}