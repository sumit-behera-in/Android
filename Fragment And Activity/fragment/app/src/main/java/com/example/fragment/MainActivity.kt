package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

   // private val fragment1:Fragment = Fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val f1:Button = findViewById(R.id.Fragment1_bttn)
        val f2 : Button = findViewById(R.id.fragment2_button)
        val fragmentManager:FragmentManager = supportFragmentManager
        val arr = arrayOf(f1,f2)
        for(items in arr){
            items.setOnClickListener {
                val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
                when (items){
                    f1-> fragmentTransaction.replace(R.id.framelayout,Fragment1())
                    f2 -> fragmentTransaction.replace(R.id.framelayout,Fragment2())
                }
                fragmentTransaction.commit()
            }
        }
    }
}