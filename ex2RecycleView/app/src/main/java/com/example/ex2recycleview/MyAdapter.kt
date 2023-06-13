package com.example.ex2recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val data:ArrayList<ModelClass>): RecyclerView.Adapter<MyAdapter.holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view :View = layoutInflater.inflate(R.layout.single_row, parent,false)
        return holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.img.setImageResource(data.get(position).imgnam)
        holder.title.text = data.get(position).header
        holder.desc.text = data.get(position).desc
    }
    class holder(itemView:View):RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img1)
        val title = itemView.findViewById<TextView>(R.id.txt1)
        val desc:TextView = itemView.findViewById(R.id.txt2)
    }
}