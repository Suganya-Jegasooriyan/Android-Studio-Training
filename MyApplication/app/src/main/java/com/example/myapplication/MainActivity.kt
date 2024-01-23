package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  myImageView: ImageView = findViewById(R.id.imageView3)
        myImageView.setOnClickListener{
            myImageView.setBackgroundColor(Color.BLACK)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("=============", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("===============", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("=============", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("==============", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("==============", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    fun onImageButtonClick(view: View) {}
}