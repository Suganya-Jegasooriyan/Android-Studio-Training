package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvTextList = findViewById<RecyclerView>(R.id.rv_textList)
        rvTextList.layoutManager = LinearLayoutManager(this)
        rvTextList.adapter = RecyclerViewAdapter()
    }
}