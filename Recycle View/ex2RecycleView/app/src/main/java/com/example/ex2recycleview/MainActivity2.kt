package com.example.ex2recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val img = findViewById<ImageView>(R.id.desc_img)
        val title = findViewById<TextView>(R.id.desc_title)
        val desc = findViewById<TextView>(R.id.desc_text)

        img.setImageResource(intent.getIntExtra("imageName",0))
        title.text=intent.getStringExtra("header")
        desc.text = intent.getStringExtra("desc")

    }
}