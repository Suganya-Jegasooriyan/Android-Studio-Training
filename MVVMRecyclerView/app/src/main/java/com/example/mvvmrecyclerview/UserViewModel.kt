package com.example.mvvmrecyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userItemLiveData = MutableLiveData<ApiResponse<UserItem>>()
    private val userRepository = UserItemRepository()

    fun getPostByIdLiveData(): MutableLiveData<ApiResponse<UserItem>> {
        return userItemLiveData
    }

    fun setUserLiveData(id: Int) {
        userRepository.makeUserAPICall(id, userItemLiveData)
    }
}

