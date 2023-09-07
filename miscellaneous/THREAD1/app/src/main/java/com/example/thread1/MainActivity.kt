package com.example.thread1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.thread1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var status = false
    var counter =0
    val TAG = "mainActivity"
    //thread handler is required for accessing a view
    private lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(mainLooper)

        binding.start.setOnClickListener {
            status=true
            Thread {
                while (status) {
                    counter++
                    Thread.sleep(2000)
                    Log.i(TAG,"count = $counter")
                    handler.post {
                        binding.text.text = "count = $counter"
                    }
                }
            }.start()

        }
        binding.stop.setOnClickListener {
            status = false
        }

        binding.reset.setOnClickListener {
            status=false
            counter=0
        }
    }
}