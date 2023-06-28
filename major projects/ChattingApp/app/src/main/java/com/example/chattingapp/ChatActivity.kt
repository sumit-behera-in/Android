package com.example.chattingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messageBox:EditText
    private lateinit var sendBox:ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private var senderRoom:String? = null
    private var receiverRoom:String ?= null
    private lateinit var mDbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

//        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        val email = intent.getStringExtra("email")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        senderRoom = receiverUid+senderUid
        receiverRoom = senderUid+receiverUid
        mDbRef = FirebaseDatabase.getInstance().reference

        supportActionBar?.title = email.toString()

        messageRecyclerView = findViewById(R.id.chatRecycle)
        messageBox = findViewById(R.id.messageBox)
        sendBox = findViewById(R.id.sendBox)

        messageList = arrayListOf()
        messageAdapter = MessageAdapter(this,messageList)


        messageRecyclerView.layoutManager = LinearLayoutManager(this)
        messageRecyclerView.adapter = messageAdapter

        //add data to recycle View
        mDbRef.child("Chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object  :ValueEventListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for(postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        //add message to database
        sendBox.setOnClickListener{

            val message = messageBox.text.toString()
            val messageObject = Message(message,senderUid)

            mDbRef.child("Chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("Chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }

            messageBox.setText("")
        }
    }
}