package com.example.chattingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPass : EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup:Button

    private lateinit var Mauth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        Mauth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.login_Email)
        editPass = findViewById(R.id.login_pass)
        btnLogin = findViewById(R.id.btn_Login)
        btnSignup = findViewById(R.id.btn_Signup)

        btnSignup.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val pass = editPass.text.toString()

            if(email=="" || pass ==""){
                Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()
            }
            else {
                login(email, pass)
            }
        }

    }

    private fun login(email: String, pass: String) {
        //Login Logic
        Mauth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    val uid:String = Mauth.currentUser?.uid!!

                    // Sign in success, update UI with the signed-in user's information
                   val intent = Intent(this,MainActivity::class.java)
                    finish() // clear current activity from back stack
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Invalid User",Toast.LENGTH_SHORT).show()


                }
            }
    }
}