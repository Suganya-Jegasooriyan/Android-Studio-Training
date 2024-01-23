package com.example.viewpagersample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    private var instance :FirstFragment? = null
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                if (instance == null ) {
                    instance = FirstFragment()
                    instance as FirstFragment
                } else
                    instance as FirstFragment
            }

            1 -> {
                SecondFragment()
            }

            2 -> {
                ThirdFragment()
            }

            3 -> {
                FourthFragment()
            }

            4 -> {
                FifthFragment()
            }

            else -> {
                FirstFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Tab 1"
            }

            1 -> {
                return "Tab 2"
            }

            2 -> {
                return "Tab 3"
            }

            3 -> {
                return "Tab 4"
            }

            4 -> {
                return "Tab 5"
            }
        }
        return super.getPageTitle(position)
    }
}