package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private lateinit var button1:Button
    private lateinit var button2:Button
    private lateinit var button3:Button
    private lateinit var button4:Button
    private lateinit var button5:Button
    private lateinit var button6:Button
    private lateinit var button7:Button
    private lateinit var button8:Button
    private lateinit var button9:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1= findViewById(R.id.button1)
        button2= findViewById(R.id.button2)
        button3= findViewById(R.id.button3)
        button4= findViewById(R.id.button4)
        button5= findViewById(R.id.button5)
        button6= findViewById(R.id.button6)
        button7= findViewById(R.id.button7)
        button8= findViewById(R.id.button8)
        button9= findViewById(R.id.button9)

        val playerName:TextView = findViewById(R.id.player)

        val arr:MutableList<Button> = mutableListOf(button1,button2,button3,
                                                    button4,button5,button6,
                                                    button7,button8,button9)
        val player1Select:MutableList<Button> = mutableListOf()
        val player2Select:MutableList<Button> = mutableListOf()

        for (item in arr){

            item.setOnClickListener {
                if(arr.contains(item)) {
                    if(playerName.text==getString(R.string.player_1)){
                        item.text=getString(R.string.x)
                        arr.remove(item)
                        player1Select.add(item)
                        playerName.text = getString(R.string.player_2)
                        cheak(player1Select,getString(R.string.p1won),arr)

                    }
                    else{
                        item.text=getString(R.string.o)
                        arr.remove(item)
                        player2Select.add(item)
                        playerName.text = getString(R.string.player_1)
                        cheak(player2Select,getString(R.string.p2won),arr)

                    }

                }
            }
        }
    }

    private fun cheak(list: MutableList<Button>,name:String,arr:MutableList<Button>) {
        if(arr.size==0) {
            findViewById<TextView>(R.id.player).text = "Tie"
            findViewById<ConstraintLayout>(R.id.bord).visibility = View.GONE

        }
        else if((list.contains(button1) && list.contains(button4) && list.contains(button7)) ||
            (list.contains(button2) && list.contains(button5) && list.contains(button8)) ||
            (list.contains(button3) && list.contains(button6) && list.contains(button9)) ||
            (list.contains(button1) && list.contains(button2) && list.contains(button3)) ||
            (list.contains(button4)&&list.contains(button5) && list.contains(button6))||
            (list.contains(button7) && list.contains(button8) && list.contains(button9))||
            (list.contains(button1) && list.contains(button5) && list.contains(button9))||
            (list.contains(button7)&& list.contains(button5) && list.contains(button3))){

            findViewById<TextView>(R.id.player).text = name
            findViewById<ConstraintLayout>(R.id.bord).visibility = View.GONE

        }
    }


}