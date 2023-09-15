package com.example.myfoodapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainDishFragment()
            1 -> SideDishFragment()
            2 -> DessertFragment()
            3 -> BeverageFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}