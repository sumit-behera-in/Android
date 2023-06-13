package com.example.navex3.onBording

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.lifecycle.Lifecycle


class ViewPagerAdapter(
    list:ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle)
    :FragmentStateAdapter(fm,lifecycle) {

    private val fragmentlist:ArrayList<Fragment> = list
    override fun getItemCount(): Int {
        return fragmentlist.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentlist[position]
    }
}