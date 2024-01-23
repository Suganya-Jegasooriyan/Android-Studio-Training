package com.example.viewpagerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("OnCreate()")
        viewPager = findViewById(R.id.viewPager)
        val fragment1 = FirstFragment()
        val fragment2 = SecondFragment()
        val fragment3 = ThirdFragment()
        val fragment4 = FourthFragment()
        val fragment5 = FirstFragment()
        val fragments = listOf(fragment1, fragment2, fragment3, fragment4, fragment5)
        viewPager.adapter = PageAdapter(fragments,supportFragmentManager, lifecycle)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }
}