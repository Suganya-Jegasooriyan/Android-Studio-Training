package com.example.mvvmrecyclerview

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserItemRepository {
    private val api: UserApiInterface =
        RetrofitHelper.getInstance().create(UserApiInterface::class.java)

    fun makeUserListAPICall(userItemListLiveData: MutableLiveData<ApiResponse<List<UserItem>>>) {

        api.getUserItem().enqueue(object : Callback<List<UserItem>?> {
            override fun onResponse(
                call: Call<List<UserItem>?>,
                response: Response<List<UserItem>?>
            ) {
                userItemListLiveData.postValue(ApiResponse(success = response.body()))
            }

            override fun onFailure(call: Call<List<UserItem>?>, t: Throwable) {
                userItemListLiveData.postValue(ApiResponse(error = t.message))
            }

        })
    }

    fun makeUserAPICall(id: Int, userItemLiveData: MutableLiveData<ApiResponse<UserItem>>) {

        api.getUserById(id).enqueue(object : Callback<UserItem?> {
            override fun onResponse(call: Call<UserItem?>, response: Response<UserItem?>) {
                userItemLiveData.postValue(ApiResponse(success = response.body()))
            }

            override fun onFailure(call: Call<UserItem?>, t: Throwable) {
                userItemLiveData.postValue(ApiResponse(error = t.message))
            }

        })
    }
}