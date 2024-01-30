package com.example.mvvmrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayDetailsActivity : AppCompatActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_details)
        initView()
    }

    private fun initView() {
        tvTitle = findViewById(R.id.tv_output_title)
        tvBody = findViewById(R.id.tv_output_body)
        tvTitle.text = intent.getStringExtra("title")
        tvBody.text = intent.getStringExtra("body")
    }
}