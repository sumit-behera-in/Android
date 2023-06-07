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

class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_1, container, false)
        val button:Button = view.findViewById(R.id.buttonF1)
        button.setOnClickListener{
            val editText:EditText = view.findViewById(R.id.EditFrag1)
            val result:Bundle = Bundle()
            result.putString("df1",editText.text.toString())
            parentFragmentManager.setFragmentResult("datafrag1",result)
            editText.setText(" ")
        }

        parentFragmentManager.setFragmentResultListener("datafrag2",this, FragmentResultListener { requestKey, result ->
            val data:String? = result.getString("df2")
            val textView: TextView = view.findViewById(R.id.Frag1)
            textView.text = data

        })

        return view
    }


}