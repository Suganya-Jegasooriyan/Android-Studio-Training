package com.example.mvvmrecyclerview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiInterface {
    @GET("posts")
    fun getUserItem(): Call<List<UserItem>>

    @GET("posts/{id}")
    fun getUserById(@Path("id") id: Int): Call<UserItem>
}