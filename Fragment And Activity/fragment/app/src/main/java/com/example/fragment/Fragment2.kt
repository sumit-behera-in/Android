package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentResultListener


class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_2, container, false)

        val button: Button = view.findViewById(R.id.buttonF2)
        button.setOnClickListener{
            val editText: EditText = view.findViewById(R.id.EditFrag2)
            val result:Bundle = Bundle()
            result.putString("df2",editText.text.toString())
            parentFragmentManager.setFragmentResult("datafrag2",result)
            editText.setText(" ")
        }

        parentFragmentManager.setFragmentResultListener("datafrag1",this, FragmentResultListener { requestKey, result ->
            val data:String? = result.getString("df1")
            val textView:TextView = view.findViewById(R.id.Frag2)
            textView.text = data

        })

        return view
    }

}