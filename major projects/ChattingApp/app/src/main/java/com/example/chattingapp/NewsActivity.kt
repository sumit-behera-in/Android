package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth

class NewsActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    private  val url ="https://gitlab.com/sumitbehera1508/Android/-/tree/main/major%20projects/ChattingApp?id=com.example.chattingapp"

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

}