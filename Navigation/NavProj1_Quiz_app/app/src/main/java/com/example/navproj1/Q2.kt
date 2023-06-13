package com.example.navproj1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation

class Q2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_q2, container, false)
        val optionA:Button = view.findViewById(R.id.Q1a)
        val optionB:Button = view.findViewById(R.id.Q1b)
        val optionC:Button = view.findViewById(R.id.Q1c)
        val optionD:Button = view.findViewById(R.id.Q1d)
        val next:Button = view.findViewById(R.id.next)
        val retry:Button = view.findViewById(R.id.retry)
        val pre:Button = view.findViewById(R.id.Prev)
        val buttons = arrayOf(optionA,optionC,optionD)

        optionB.setOnClickListener {
            Toast.makeText(activity,"Correct Answer", Toast.LENGTH_SHORT).show()
            next.visibility = View.VISIBLE
            retry.visibility = View.GONE
        }
        for(items in buttons){
            items.setOnClickListener {
                Toast.makeText(activity,"Wrong Answer", Toast.LENGTH_SHORT).show()
                retry.visibility = View.VISIBLE
                next.visibility = View.GONE
            }
        }

        next.setOnClickListener {
            val action = Q2Directions.actionQ2ToQ3()
            Navigation.findNavController(view).navigate(action)
        }

        pre.setOnClickListener {
            val action = Q2Directions.actionQ2ToQ1()
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

}