package com.example.navex2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class HomeFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val veiw = inflater.inflate(R.layout.fragment_home, container, false)

        veiw.findViewById<Button>(R.id.Setting).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFrag_to_SettingFrag)
        }

        veiw.findViewById<Button>(R.id.Person).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFrag_to_PersonFrag)
        }

        return veiw
    }
}