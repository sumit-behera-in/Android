package com.example.ex1recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var  recyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView= findViewById(R.id.recycleView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val array = arrayOf("Jio","Airtel","Bsnl","VI","MTNL","MTS","TMobile","Zong","Jio","Airtel","Bsnl","VI","MTNL","MTS","TMobile","Zong","Jio","Airtel","Bsnl","VI","MTNL","MTS","TMobile","Zong","Jio","Airtel","Bsnl","VI","MTNL","MTS","TMobile","Zong")
        recyclerView.adapter = myAdapter(array)


    }
}