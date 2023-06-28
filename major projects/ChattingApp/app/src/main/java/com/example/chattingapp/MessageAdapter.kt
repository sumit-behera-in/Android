package com.example.chattingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val itemSent = 2;
    val itemReceived = 1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1){
            //itemReceived
            val view = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return MessageAdapter.ReceiveViewHolder(view)
        }
        else{
            val view = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return MessageAdapter.SentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
       return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){
            //sent view holder

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        }

        else{
            //receive view holder

            val viewHolder = holder as ReceiveViewHolder
            holder.reciveMessage.text = currentMessage.message
        }


    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return itemSent
        }
        else {
            return itemReceived
        }
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView ){

        val sentMessage:TextView = itemView.findViewById(R.id.txt_message_sent)

    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView ){

        val reciveMessage:TextView = itemView.findViewById(R.id.txt_message_receive)
    }
}