package com.example.retrofitsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.OnClick{

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var progressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE
        getMyData()
    }

    private fun getMyData() {

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofitBuilder.create(UserInterface::class.java)

        api.getData().enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                progressBar.visibility = View.INVISIBLE
                recyclerView.adapter = RecyclerViewAdapter(response.body() , this@MainActivity)
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }
        })
    }

    override fun passData(data: String, tit: String) {
        val intent = Intent(this,DisplayDetailsActivity::class.java)
        intent.putExtra("value",data)
        intent.putExtra("title", tit)
        startActivity(intent)
    }

}