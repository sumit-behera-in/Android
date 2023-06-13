package com.example.exp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

lateinit var diceImage:ImageView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton:Button=findViewById(R.id.rollButton)
        diceImage = findViewById(R.id.dice_image)
        rollButton.setOnClickListener{
            rollDice()
        }
    }

    private fun rollDice() {
        val my_list= arrayListOf<String>("1","2","3","4","5","6")
        val drawableResource = when(my_list.random()){
            "1"->R.drawable.dice1
            "2"->R.drawable.dice2
            "3"->R.drawable.dice3
            "4"->R.drawable.dice4
            "5"->R.drawable.dice5
            else ->R.drawable.dice6
        }
        diceImage.setImageResource(drawableResource)
    }
}