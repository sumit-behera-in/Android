package com.example.todo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections


class MainActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView =findViewById<RecyclerView>(R.id.recycle)
        recycleView.layoutManager = LinearLayoutManager(this)

        val arr:ArrayList<String> = arrayListOf()
        var myAdapter =  MyAdapter(arr)
        recycleView.adapter= myAdapter


        findViewById<Button>(R.id.button).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do You Want TO Add the Task")
                .setPositiveButton("Yes"){
                    dialog,id -> val text:String = findViewById<EditText>(R.id.editText).text.toString()
                    arr.add(text)
                    findViewById<EditText>(R.id.editText).text.clear()
                    myAdapter.notifyDataSetChanged()
                    Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){
                    dialog,id -> dialog.cancel()
                    Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show()
                }

            val alert = builder.create()
            alert.setTitle("To Do App")
            alert.show()

        }

        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from_pos = viewHolder.absoluteAdapterPosition
                val to_pos = target.absoluteAdapterPosition
                Collections.swap(arr,from_pos,to_pos)
                myAdapter.notifyItemMoved(from_pos,to_pos)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Take action for the swiped item
                when(direction){
                    ItemTouchHelper.LEFT->{
                        myAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
                    }
                    ItemTouchHelper.RIGHT->{
                        val archivedItem = arr[viewHolder.absoluteAdapterPosition]
                        myAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
                        myAdapter.addItem(arr.size,archivedItem)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycleView)


    }

}