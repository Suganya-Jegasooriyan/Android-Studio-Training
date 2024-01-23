package com.example.viewpagerexample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(
    private val fragments: List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val map = mutableMapOf<Int,Fragment>()

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment  {
        when(position) {
            0 -> {
                return if (map.containsKey(position)) {
                    map[position]!!
                } else {
                    val frag = FirstFragment()
                    map[position] = frag
                    frag
                }
            }
            1 -> {
                return if (map.containsKey(position)) {
                    map[position]!!
                } else {
                    val frag = SecondFragment()
                    map[position] = frag
                    frag
                }
            }
            2 -> {
                return if (map.containsKey(position)) {
                    map[position]!!
                } else {
                    val frag = ThirdFragment()
                    map[position] = frag
                    frag
                }
            }
            3 -> {
                return if (map.containsKey(position)) {
                    map[position]!!
                } else {
                    val frag = FourthFragment()
                    map[position] = frag
                    frag
                }
            }
            4 -> {
                return if (map.containsKey(position)) {
                    map[position]!!
                } else {
                    val frag = FifthFragment()
                    map[position] = frag
                    frag
                }
            }
            else -> return FirstFragment()
        }
    }
}