package com.example.aboutme

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityMainBinding
    private val myName:MyName=MyName("Monkey D. Luffy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.myName = myName
        //val button:Button= findViewById(R.id.button)
       //val txt:TextView=findViewById(R.id.bio_text)
        binding.button.setOnClickListener {
            //if(binding.bioText.text== getString(R.string.com))
            if(binding.bioText.text==myName.com){
                //binding.bioText.text = getString(R.string.bio)
                binding.bioText.text=myName.bio
                //binding.button.text=getString(R.string.completed)
                binding.button.text=myName.completed
            }
            else {
                Toast.makeText( applicationContext,"completed", Toast.LENGTH_SHORT).show()
                //binding.bioText.text = getString(R.string.com)
                binding.bioText.text=myName.com
                //binding.button.text = getString(R.string.ragain)
                binding.button.text= myName.rAgain
            }
        }
        //val submit:Button=findViewById(R.id.submit)
        //val nickNameText:TextView=findViewById(R.id.nickname_text)
        //val nickNameEdit:EditText=findViewById(R.id.nickname_edit)
        binding.submit.setOnClickListener {
            //binding.nicknameText.text=binding.nicknameEdit.text
            myName?.nickName=binding.nicknameEdit.text.toString()
            binding.nicknameText.text=myName.nickName
            binding.nicknameText.visibility= View.VISIBLE
            binding.nicknameEdit.visibility = View.GONE
            binding.submit.visibility = View.GONE
        }
    }
}