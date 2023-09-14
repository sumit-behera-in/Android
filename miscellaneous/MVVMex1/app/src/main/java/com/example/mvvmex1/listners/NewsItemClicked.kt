package com.example.mvvmex1.listners

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.mvvmex1.News
import com.example.mvvmex1.activity.NewsActivity2
import com.example.mvvmex1.adapter.NewsAdapter

interface NewsItemClicked {
    fun onItemClicked(item: News,context: Context,url: String,imgUrl:String,text:String,desc:String){
       val intent = Intent(context,NewsActivity2::class.java)
        intent.putExtra("url",url)
        context.startActivity(intent)
    }

    fun shareMe(url:String,mycontext: Context){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type= "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,url)
        val chooser = Intent.createChooser(intent,"Share Using")
        mycontext.startActivity(chooser)
    }

    fun loadMe(view: View, context: Context,adapter:NewsAdapter,url: String){
//        val textView = view.findViewById<TextView>(R.id.title)
//        val imageView = view.findViewById<ImageView>(R.id.image)
// ...

// Instantiate the RequestQueue.

// Request a string response from the provided URL.
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET, url,null, {
                // Display the first 500 characters of the response string.

                val newsArray = ArrayList<News>()
                val newJsonArray = it.getJSONArray("articles")
                for(i in 0 until newJsonArray.length()){
                    val newsObject = newJsonArray.getJSONObject(i)
                    val news = News(
                        newsObject.getString("title"),
                        newsObject.getString("author"),
                        newsObject.getString("url"),
                        newsObject.getString("urlToImage"),
                        newsObject.getString("content"),
                        newsObject.getString("description")
                    )

                    newsArray.add(news)
                }
                adapter.updateNews(newsArray)
            },
            {
                Toast.makeText(context,"Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"]="Mozilla/5.0"
                return headers
            }
        }

// Add the request to the RequestQueue.

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }
}