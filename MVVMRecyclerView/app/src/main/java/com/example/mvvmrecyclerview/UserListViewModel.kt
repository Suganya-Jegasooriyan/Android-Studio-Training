package com.example.mvvmrecyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {
    private val userItemListLiveData = MutableLiveData<List<UserItem>>()
    private val userListRepository = UserItemRepository()

    fun getPostListLiveData(): MutableLiveData<List<UserItem>> {
        return userItemListLiveData
    }

    fun setUserListLiveData() {
        userListRepository.makeUserListAPICall(userItemListLiveData)
    }
}