package com.example.mvvmrecyclerview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var dataList  = MutableLiveData<List<UserItem>>()
    init {
        dataList = MutableLiveData()
    }

    fun getLiveDataObserver() : MutableLiveData<List<UserItem>> {
        return dataList
    }
    fun makeAPICall(){
        val retroInstance = RetrofitHelper.getInstance()
        val api = retroInstance.create(UserApiInterface::class.java)
        api.getData().enqueue(object : Callback<List<UserItem>?> {
            override fun onResponse(
                call: Call<List<UserItem>?>,
                response: Response<List<UserItem>?>
            ) {
                val list = response.body()
                dataList.postValue(list)
            }

            override fun onFailure(call: Call<List<UserItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }

        })
    }
}

