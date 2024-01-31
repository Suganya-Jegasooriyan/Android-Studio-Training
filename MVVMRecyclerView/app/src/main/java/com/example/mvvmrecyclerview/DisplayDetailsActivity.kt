package com.example.mvvmrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class DisplayDetailsActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var tvId: TextView
    private lateinit var tvUserId: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_details)
        tvId = findViewById(R.id.tv_output_id)
        tvUserId = findViewById(R.id.tv_output_user_id)
        tvTitle = findViewById(R.id.tv_output_title)
        tvBody = findViewById(R.id.tv_output_body)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        id = intent.getStringExtra("id")!!.toInt()
        setUserItem()
    }

    private fun setUserItem() {
        userViewModel.setUserLiveData(id)
        userViewModel.getPostByIdLiveData().observe(this, Observer {
            tvId.text = it.id.toString()
            tvUserId.text = it.userId.toString()
            tvTitle.text = it.title
            tvBody.text = it.body
        })
    }
}