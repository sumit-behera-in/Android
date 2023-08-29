package com.example.todo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data:ArrayList<String>):RecyclerView.Adapter<MyAdapter.holder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(i:Int){
        data.removeAt(i)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addItem(i:Int, item:String){
        data.add(i,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view :View = LayoutInflater.from(parent.context).inflate(R.layout.single_row,parent,false)
        return holder(view)
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.text.text=data[position]

    }

    override fun getItemCount(): Int {
        return data.size
    }
    class holder(itemView: View):RecyclerView.ViewHolder(itemView){
        val text =itemView.findViewById<TextView>(R.id.textView)

    }
}