package com.example.multiplicationtable

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TableActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        val num = intent.getIntExtra("num",0)
        findViewById<TextView>(R.id.table1).text = "$num X 1 = ${num*1}"
        findViewById<TextView>(R.id.table2).text = "$num X 2 = ${num*2}"
        findViewById<TextView>(R.id.table3).text = "$num X 3 = ${num*3}"
        findViewById<TextView>(R.id.table4).text = "$num X 4 = ${num*4}"
        findViewById<TextView>(R.id.table5).text = "$num X 5 = ${num*5}"
        findViewById<TextView>(R.id.table6).text = "$num X 6 = ${num*6}"
        findViewById<TextView>(R.id.table7).text = "$num X 7 = ${num*7}"
        findViewById<TextView>(R.id.table8).text = "$num X 8 = ${num*8}"
        findViewById<TextView>(R.id.table9).text = "$num X 9 = ${num*9}"
        findViewById<TextView>(R.id.table10).text = "$num X 10 = ${num*10}"

    }
}