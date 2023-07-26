package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class gpt : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    private val client = OkHttpClient()
    private  val url ="https://gitlab.com/sumitbehera1508/Android/-/tree/main/major%20projects/ChattingApp?id=com.example.chattingapp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpt)

        val question = findViewById<EditText>(R.id.question)
        val submit = findViewById<Button>(R.id.submit)
        val output = findViewById<TextView>(R.id.output)

        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.teal_700))
        supportActionBar?.title = mAuth.currentUser?.email

        submit.setOnClickListener {
            val q = question.text.toString().trim()
            if(q.isNotEmpty()){
                output.text = "Please Wait...."
                getResponse(q){response ->
                    runOnUiThread{
                        output.text = response
                        output.background = getDrawable(R.drawable.send_background)
                        output.setTextColor(resources.getColor(R.color.purple_700))
                    }

                }
            }
            else{
                output.text = "Enter Some text"
            }
        }

        findViewById<TextView>(R.id.gpt).setTextColor(resources.getColor(R.color.purple_500))

        findViewById<TextView>(R.id.Chats).setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.news).setOnClickListener {
            val intent = Intent(this,NewsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getResponse(q: String, callBack: (String) -> Unit) {
        val url="https://api.openai.com/v1/engines/text-davinci-003/completions"
        val ApiKey = "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"
        val requestBody = """
            {
                "prompt": "$q",
                "max_tokens": 1000,
                "temperature": 0
            }
        """.trimIndent()


        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type","application/json")
            .addHeader("Authorization","Bearer $ApiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.v("error","Api failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                if (body != null) {
                    Log.v("data",body)
                }
                else{
                    Log.v("data","empty")
                }

                val jsonObject = JSONObject(body)
                val jsonArray:JSONArray = jsonObject.getJSONArray("choices")
                val textResult = jsonArray.getJSONObject(0).getString("text")

                callBack(textResult)
            }

        })
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