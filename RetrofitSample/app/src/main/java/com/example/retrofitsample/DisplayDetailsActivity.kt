package com.example.retrofitsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayDetailsActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val output = findViewById<TextView>(R.id.tv_output)
        val titleOutput = findViewById<TextView>(R.id.tv_title_output)
         output.text = intent.getStringExtra("value")
        titleOutput.text = intent.getStringExtra("title")
    }

}