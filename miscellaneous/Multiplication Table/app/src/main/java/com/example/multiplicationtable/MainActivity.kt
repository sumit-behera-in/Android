package com.example.multiplicationtable

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enter:Button = findViewById(R.id.enter)
        val text= findViewById<EditText>(R.id.editText)

        enter.setOnClickListener {

            // add dialog box ....

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to proceed with ${text.text} ?")
                .setPositiveButton("Yes") {
                    dialog,id->
                    if(text.text.toString().isDigitsOnly()){

                        // send data to next activity
                        if(text.text.length>9){
                            Toast.makeText(this,"Input is Too Large",Toast.LENGTH_SHORT).show()
                        }
                        else if (text.text.isEmpty()){
                            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val intent = Intent(this,TableActivity::class.java)
                            intent.putExtra("num",text.text.toString().toInt())
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                            text.text.clear()
                            startActivity(intent)
                        }
                    }
                    else{
                        Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                    }

                }
                .setNegativeButton("No"){
                    dialog,id->
                    dialog.cancel()
                    Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
                }
            val alert = builder.create()
            alert.setTitle("Math Table App")
            alert.show()
        }
    }
}
