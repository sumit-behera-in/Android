package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth

class NewsActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
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
    }
}