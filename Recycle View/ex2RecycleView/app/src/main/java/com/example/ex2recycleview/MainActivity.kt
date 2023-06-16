package com.example.ex2recycleview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import java.util.Collections
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val tempArray: ArrayList<ModelClass> = arrayListOf<ModelClass>()
    private val hold:ArrayList<ModelClass> = arrayListOf<ModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Recycle View Demo 2"

        val rcv = findViewById<RecyclerView>(R.id.recycleView)
        //vertical view
        //rcv.layoutManager = LinearLayoutManager(this)

        //horizontal view
        //val myLayoutManager:LayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //rcv.layoutManager = myLayoutManager

        //grid view
        val gridLayoutManager = GridLayoutManager(this,1)
        rcv.layoutManager = gridLayoutManager

        val myAdapter  = MyAdapter(dataque(),applicationContext)
        rcv.adapter = myAdapter

        //swipeGesture
        val swipeGesture = object : SwipeGesture(this){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from_pos = viewHolder.absoluteAdapterPosition
                val to_pos = target.absoluteAdapterPosition
                Collections.swap(hold,from_pos,to_pos)
                myAdapter.notifyItemMoved(from_pos,to_pos)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT->{
                        myAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
                    }
                    ItemTouchHelper.RIGHT->{
                        val archivedItem = tempArray[viewHolder.absoluteAdapterPosition]
                        myAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
                        myAdapter.addItem(tempArray.size,archivedItem)
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(rcv)
    }
    private fun dataque():ArrayList<ModelClass>{


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

        tempArray.addAll(hold)
        return tempArray
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        val item:MenuItem? = menu?.findItem(R.id.search_menu)
        val searchView:SearchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                tempArray.clear()
                val searchText = newText!!.lowercase()
                if (searchText.isNotEmpty()) {
                    hold.forEach {
                        if (it.header.lowercase().contains(searchText)) {
                            tempArray.add(it)
                        }
                    }
                    findViewById<RecyclerView>(R.id.recycleView).adapter!!.notifyDataSetChanged()
                }else{
                    tempArray.clear()
                    tempArray.addAll(hold)
                    findViewById<RecyclerView>(R.id.recycleView).adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
