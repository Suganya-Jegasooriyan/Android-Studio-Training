package com.example.mvvmrecyclerview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserItemRepository {
    private val api: UserApiInterface =
        RetrofitHelper.getInstance().create(UserApiInterface::class.java)

    fun makeUserListAPICall(userItemListLiveData: MutableLiveData<List<UserItem>>) {

        api.getUserItem().enqueue(object : Callback<List<UserItem>?> {
            override fun onResponse(
                call: Call<List<UserItem>?>,
                response: Response<List<UserItem>?>
            ) {
                userItemListLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }

        })
    }

    fun makeUserAPICall(id: Int, userItemLiveData: MutableLiveData<UserItem>) {

        api.getUserById(id).enqueue(object : Callback<UserItem?> {
            override fun onResponse(call: Call<UserItem?>, response: Response<UserItem?>) {
                userItemLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserItem?>, t: Throwable) {
                Log.d("DisplayDetailsActivity", "onFailure:" + t.message)
            }

        })
    }
}