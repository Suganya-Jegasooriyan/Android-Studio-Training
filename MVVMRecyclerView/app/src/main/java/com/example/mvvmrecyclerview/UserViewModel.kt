package com.example.mvvmrecyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userItemLiveData = MutableLiveData<UserItem>()
    private val userRepository = UserItemRepository()

    fun getPostByIdLiveData(): MutableLiveData<UserItem> {
        return userItemLiveData
    }

    fun setUserLiveData(id: Int) {
        userRepository.makeUserAPICall(id, userItemLiveData)
    }
}

