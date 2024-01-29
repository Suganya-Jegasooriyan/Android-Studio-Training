package com.example.mvvmrecyclerview

import retrofit2.Call
import retrofit2.http.GET

interface UserApiInterface {
    @GET("posts")
    fun getData() : Call<List<UserItem>>
}