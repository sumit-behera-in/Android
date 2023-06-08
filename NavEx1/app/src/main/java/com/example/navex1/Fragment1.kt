package com.example.navex1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import  com.example.navex1.Fragment1Directions


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_1, container, false)
        val frag1TextView:TextView = view.findViewById(R.id.frag1_text)
        frag1TextView.setOnClickListener {
            val action = Fragment1Directions.actionFragment1ToFragment2()
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }

}