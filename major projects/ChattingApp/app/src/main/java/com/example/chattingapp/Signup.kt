package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private lateinit var editName:EditText
    private lateinit var editEmail: EditText
    private lateinit var editPass : EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    private lateinit var Mauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        editEmail = findViewById(R.id.edit_Email)
        editName=findViewById(R.id.edit_Name)
        editPass = findViewById(R.id.edit_pass)
        btnSignup = findViewById(R.id.btn_Signup)
        btnLogin = findViewById(R.id.btn_Login)

        Mauth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener { 
            val email= editEmail.text.toString()
            val pass = editPass.text.toString()
            
            signUp(email,pass)
        }
    }

    private fun signUp(email: String, pass: String) {
        //Logic for Creating User
        Mauth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some err Occurred ",Toast.LENGTH_SHORT).show()
                }
            }
    }
}