package com.example.mvvmrecyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {
    private val userItemListLiveData = MutableLiveData<ApiResponse<List<UserItem>>>()
    private val userListRepository = UserItemRepository()

    fun getPostListLiveData(): MutableLiveData<ApiResponse<List<UserItem>>> {
        return userItemListLiveData
    }

    fun setUserListLiveData() {
        userListRepository.makeUserListAPICall(userItemListLiveData)
    }
}