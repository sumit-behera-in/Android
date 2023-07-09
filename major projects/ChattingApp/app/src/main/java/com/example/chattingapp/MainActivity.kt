package com.example.chattingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var  userRecyclerView: RecyclerView
    private lateinit var  userList: ArrayList<User>
    private lateinit var adapter: UserAdapter

    private  val url ="https://gitlab.com/sumitbehera1508/Android/-/tree/main/major%20projects/ChattingApp?id=com.example.chattingapp"

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.Chats).setTextColor(resources.getColor(R.color.purple_500))

        mAuth = FirebaseAuth.getInstance()
        mDbref = FirebaseDatabase.getInstance().reference
        userRecyclerView = findViewById(R.id.userRecycle)

        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.teal_700))
        supportActionBar?.title = mAuth.currentUser?.email


        userList = arrayListOf()
        adapter = UserAdapter(this,userList)


        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter


        mDbref.child("User").addValueEventListener(object : ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(postsnapshot in snapshot.children){
                    val currentUser = postsnapshot.getValue(User::class.java)
                    if(currentUser?.uid != mAuth.currentUser?.uid){
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        findViewById<TextView>(R.id.gpt).setOnClickListener {
            val intent = Intent(this,gpt::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.news).setOnClickListener {
            val intent = Intent(this,NewsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.logOut){
            mAuth.signOut()
            val intent = Intent(this,Login::class.java)
         finish() // clear current activity from back stack
            startActivity(intent)
        }
        if(item.itemId==R.id.about){
            val intent = Intent(this,AboutActivity::class.java)
            finish() // clear current activity from back stack
            startActivity(intent)
        }
        if(item.itemId==R.id.share){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(intent, "Share this"))
        }

        return super.onOptionsItemSelected(item)
    }


}