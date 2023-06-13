package com.example.navex3.onBording.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.navex3.R

class SecondScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        val viewPager2:ViewPager2? = activity?.findViewById(R.id.viewPager)

        view.findViewById<TextView>(R.id.next).setOnClickListener{
            viewPager2?.currentItem = 2
        }

        return view
    }
}