package com.example.mvvmex1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmex1.News
import com.example.mvvmex1.R
import com.example.mvvmex1.listners.NewsItemClicked

class NewsAdapter(private val listner : NewsItemClicked) : RecyclerView.Adapter<Holder>() {
    private val items :ArrayList<News> = ArrayList<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_news,parent,false)
        val viewHolder = Holder(view)
        view.setOnClickListener {
            listner.onItemClicked(items[viewHolder.adapterPosition],parent.context,items[viewHolder.adapterPosition].url)
        }
        view.setOnLongClickListener {
            listner.shareMe(items[viewHolder.adapterPosition].url.toString(),parent.context)
            true
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currItem = items[position]
        holder.title.text = currItem.title
        Glide.with(holder.itemView.context).load(currItem.imageUrl).into(holder.image)


    }
    fun updateNews(it:ArrayList<News>){
        items.clear()
        items.addAll(it)
        notifyDataSetChanged()
    }


}

class Holder(itemView:View): RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.title)
    val image:ImageView = itemView.findViewById(R.id.image)

}
