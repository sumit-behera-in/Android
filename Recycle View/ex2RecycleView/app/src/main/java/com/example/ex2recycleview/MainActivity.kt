package com.example.ex2recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Recycle View Demo 2"


        val rcv : RecyclerView = findViewById(R.id.recycleView)

        //vertical view
        //rcv.layoutManager = LinearLayoutManager(this)

        //horizontal view
        //val myLayoutManager:LayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //rcv.layoutManager = myLayoutManager

        //grid view
        val gridLayoutManager = GridLayoutManager(this,2)
        rcv.layoutManager = gridLayoutManager

        val myAdapter  = MyAdapter(dataque(),applicationContext)
        rcv.adapter = myAdapter

    }
    fun dataque():ArrayList<ModelClass>{

        val hold: ArrayList<ModelClass> = ArrayList()


        //4g net work
        val net1:ModelClass = ModelClass()
        net1.header = "4g"
        net1.desc = "Most Used Network Band"
        net1.imgnam = R.drawable.baseline_4g_plus_mobiledata_24
        hold.add(net1)

        //object 1
        val obj1:ModelClass = ModelClass()
        obj1.header = "Jio 4g"
        obj1.desc = "Most user 4g network in India"
        obj1.imgnam = R.drawable.jio
        hold.add(obj1)

        //obj2
        val obj2:ModelClass = ModelClass()
        obj2.header = "VI 4g"
        obj2.desc = "Lest used 4g network in India"
        obj2.imgnam = R.drawable.vi
        hold.add(obj2)

        //obj 3
        val obj3 = ModelClass()
        obj3.header = "Airtel"
        obj3.desc = "Fastest 4g network"
        obj3.imgnam = R.drawable.airtel
        hold.add(obj3)

        //3G net
        val net2 = ModelClass()
        net2.header = "3G"
        net2.desc = "Still in use"
        net2.imgnam = R.drawable.baseline_3g_mobiledata_24
        hold.add(net2)

        //object 4
        val obj4 = ModelClass()
        obj4.header = "BSNL"
        obj4.desc = "Governent owned"
        obj4.imgnam = R.drawable.bsnl
        hold.add(obj4)

        //copy
        hold.add(net1)
        hold.add(obj1)
        hold.add(obj2)
        hold.add(obj3)
        hold.add(net2)
        hold.add(obj4)

        //copy
        hold.add(net1)
        hold.add(obj1)
        hold.add(obj2)
        hold.add(obj3)
        hold.add(net2)
        hold.add(obj4)

        //copy
        hold.add(net1)
        hold.add(obj1)
        hold.add(obj2)
        hold.add(obj3)
        hold.add(net2)
        hold.add(obj4)

        //copy
        hold.add(net1)
        hold.add(obj1)
        hold.add(obj2)
        hold.add(obj3)
        hold.add(net2)
        hold.add(obj4)

        //copy
        hold.add(net1)
        hold.add(obj1)
        hold.add(obj2)
        hold.add(obj3)
        hold.add(net2)
        hold.add(obj4)

        //copy
        hold.add(net1)
        hold.add(obj1)
        hold.add(obj2)
        hold.add(obj3)
        hold.add(net2)
        hold.add(obj4)


        return hold
    }
}