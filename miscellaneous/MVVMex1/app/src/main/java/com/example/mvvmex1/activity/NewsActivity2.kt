package com.example.mvvmex1.activity

import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.example.mvvmex1.R


class NewsActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news2)

        val web:WebView = findViewById(R.id.web)
        val url:String = intent.getStringExtra("url").toString()


        web.settings.javaScriptEnabled
        web.webViewClient
        web.settings.domStorageEnabled
        web.loadUrl(url)
        web.setOnLongClickListener {
            val intent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(this, Uri.parse(url))

            true
        }


    }
}