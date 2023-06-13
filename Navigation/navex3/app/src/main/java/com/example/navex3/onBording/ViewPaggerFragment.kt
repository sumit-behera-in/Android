package com.example.navex3.onBording

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.navex3.R
import com.example.navex3.onBording.screens.FirstScreen
import com.example.navex3.onBording.screens.SecondScreen
import com.example.navex3.onBording.screens.ThirdScreen


class ViewPaggerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_pagger, container, false)

        val fragmentList = arrayListOf<Fragment>(FirstScreen(),SecondScreen(),ThirdScreen())
        val adapter:ViewPagerAdapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        view.findViewById<ViewPager2>(R.id.viewPager).adapter=adapter

        return view
    }

}