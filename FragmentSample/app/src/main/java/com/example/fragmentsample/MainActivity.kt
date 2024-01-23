package com.example.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), Communicator {
    private val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate()-Activity")
        initView()
    }

    private fun initView() {
        val fragmentTranscation = fragmentManager.beginTransaction()
        fragmentTranscation.replace(R.id.first_fragment_container_view, FirstFragment(), FirstFragment.TAG)
        fragmentTranscation.replace(R.id.second_fragment_container_view,SecondFragment(), SecondFragment.TAG)
        fragmentTranscation.commit()
    }
    override fun onStart() {
        super.onStart()
        println("onStart()-Activity")
    }

    override fun onResume() {
        super.onResume()
        println("onResume()-Activity")
    }

    override fun onPause() {
        super.onPause()
        println("onPause()-Activity")
    }

    override fun onStop() {
        super.onStop()
        println("onStop()-Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()-Activity")
    }

    override fun passData(inputValue: String, boolean: Boolean) {
        if(boolean) {
            val secondFragment =fragmentManager.findFragmentByTag("SecondFragment") as SecondFragment
            secondFragment.passData(inputValue, boolean)
        }
        else {
            val firstFragment = fragmentManager.findFragmentByTag("FirstFragment") as FirstFragment
            firstFragment.passData(inputValue, boolean)
        }
    }
}

