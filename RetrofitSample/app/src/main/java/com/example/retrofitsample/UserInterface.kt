package com.example.retrofitsample

import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {
    @GET("posts")
    fun getData() : Call<List<MyDataItem>>
}

