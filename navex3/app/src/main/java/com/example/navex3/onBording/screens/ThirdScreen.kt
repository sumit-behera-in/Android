package com.example.navex3.onBording.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.navex3.R
class ThirdScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_third, container, false)

        view.findViewById<TextView>(R.id.FinishOnBording).setOnClickListener {
            findNavController().navigate(R.id.action_viewPaggerFragment_to_homeFragment)
            onBoardingFinished()
        }

        return view
    }
    private  fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }
}