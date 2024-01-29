package com.example.mvvmexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class UserViewModel  : ViewModel()  {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun updateUser(name: String, age: Int) {
        _user.value = User(name, age)
    }
}