package com.example.arnexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()


        val tv = findViewById<TextView>(R.id.tvHello)
        tv.setOnClickListener {
            Log.e("Teg", "on Button click")
            while (true) {
                Log.e("Test", "Printing logs!!!!!!!!!!!!!!!!!!!!!")
            }
        }


    }
}