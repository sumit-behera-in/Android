package com.example.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpt)

        val question = findViewById<EditText>(R.id.question)
        val submit = findViewById<Button>(R.id.submit)
        val output = findViewById<TextView>(R.id.output)

        supportActionBar?.title = "Chat Gpt"

        submit.setOnClickListener {
            val q = question.text.toString().trim()
            if(q.isNotEmpty()){
                output.background = getDrawable(R.color.black)
                output.text = "Please Wait...."
                getResponse(q){response ->
                    runOnUiThread{
                        output.text = response
                    }

                }
            }
            else{
                output.text = "Enter Some text"
            }
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
}