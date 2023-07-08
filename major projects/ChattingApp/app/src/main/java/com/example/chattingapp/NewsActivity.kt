package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class NewsActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    private val recyclerView = findViewById<RecyclerView>(R.id.news_Recycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        supportActionBar?.title = mAuth.currentUser?.email
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.teal_700))

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

        recyclerView.layoutManager = LinearLayoutManager(this)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.logOut){
            mAuth.signOut()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish() // clear current activity from back stack
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}