package com.rizaki.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.rizaki.challengech6Binar.database.local.User
import com.rizaki.challengech6Binar.database.local.UserRepository
import com.rizaki.challengech6Binar.database.local.UserRoomDatabase
import kotlinx.coroutines.Dispatchers
import com.rizaki.challengech6Binar.helper.UserDataStoreManager
import kotlinx.coroutines.launch


class UserViewModel(private val pref: UserDataStoreManager) : ViewModel() {

    fun saveUser(id: Int, status: Boolean) {
        viewModelScope.launch {
            pref.saveUser(id, status)
        }
    }

    fun getId(): LiveData<Int> {
        return pref.getId().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return pref.getLoginStatus().asLiveData()
    }

    fun logoutUser() {
        viewModelScope.launch {
            pref.logoutUser()
        }
    }
}