package com.example.navex1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs


class Fragment2 : Fragment() {

    private val args:Fragment2Args by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_2, container, false)
        val frag2TextView:TextView = view.findViewById(R.id.frag2_text)
        val myNumber:Int = args.number
        frag2TextView.text = myNumber.toString()
        frag2TextView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment2_to_fragment1)
        }
        return view
    }

}