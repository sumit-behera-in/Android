package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.chattingapp.adapter.VideoAdapter
import com.example.chattingapp.model.VideoModel
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NewsActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    private  val url ="https://gitlab.com/sumitbehera1508/Android/-/tree/main/major%20projects/ChattingApp?id=com.example.chattingapp"

    lateinit var viewPager2: ViewPager2
    lateinit var adapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.teal_700))
        supportActionBar?.title = mAuth.currentUser?.email

        findViewById<TextView>(R.id.news).setTextColor(resources.getColor(R.color.purple_500))

        findViewById<TextView>(R.id.Chats).setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.gpt).setOnClickListener {
            val intent = Intent(this,gpt::class.java)
            startActivity(intent)
            finish()
        }



        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewPager2 = findViewById(R.id.viewPager)

        val mDataBase = Firebase.database.getReference("videos")
        val options = FirebaseRecyclerOptions.Builder<VideoModel>()
            .setQuery(mDataBase, VideoModel::class.java)
            .build()

        adapter = VideoAdapter(options)
        viewPager2.adapter = adapter

    }
    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
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

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}

