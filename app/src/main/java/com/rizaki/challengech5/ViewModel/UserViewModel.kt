package com.rizaki.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rizaki.challengech5.database.User
import com.rizaki.challengech5.database.UserRepository
import com.rizaki.challengech5.database.UserRoomDatabase
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun checkUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkUser(email, password)
        }
    }
}