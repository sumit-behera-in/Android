package com.example.ex1recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class myAdapter(private val data:Array<String>) : RecyclerView.Adapter<myAdapter.holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_row,parent,false )
        return holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.titleText.text = data[position]
    }
    class holder(itemView:View):RecyclerView.ViewHolder(itemView){
        val titleText :TextView = itemView.findViewById(R.id.txt1)
        val titleImg : ImageView = itemView.findViewById(R.id.img1)

    }
}