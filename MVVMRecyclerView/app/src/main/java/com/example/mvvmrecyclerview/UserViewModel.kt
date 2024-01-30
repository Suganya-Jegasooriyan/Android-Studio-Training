package com.example.mvvmrecyclerview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private var userItemListLiveData = MutableLiveData<List<UserItem>>()

    init {
        userItemListLiveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<UserItem>> {
        return userItemListLiveData
    }

    fun makeAPICall() {
        val retroInstance = RetrofitHelper.getInstance()
        val api = retroInstance.create(UserApiInterface::class.java)
        api.getUserItem().enqueue(object : Callback<List<UserItem>?> {
            override fun onResponse(
                call: Call<List<UserItem>?>,
                response: Response<List<UserItem>?>
            ) {
                userItemListLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<UserItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }

        })
    }
}

