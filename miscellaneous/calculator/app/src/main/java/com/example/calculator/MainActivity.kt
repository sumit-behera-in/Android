package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var ans: Double = 0.0
    private var temp:Double = 0.0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.num0.setOnClickListener {
            temp = temp*10+0;
            binding.textView.text = temp.toString()
        }

        binding.num1.setOnClickListener {
            temp = temp*10+1;
            binding.textView.text = temp.toString()
        }

        binding.num2.setOnClickListener {
            temp = temp*10+2;
            binding.textView.text = temp.toString()
        }

        binding.num3.setOnClickListener {
            temp = temp*10+3;
            binding.textView.text = temp.toString()
        }

        binding.num4.setOnClickListener {
            temp = temp*10+4;
            binding.textView.text = temp.toString()
        }

        binding.num5.setOnClickListener {
            temp = temp*10+5;
            binding.textView.text = temp.toString()
        }
        binding.num6.setOnClickListener {
            temp = temp*10+6;
            binding.textView.text = temp.toString()
        }

        binding.num7.setOnClickListener {
            temp = temp*10+7;
            binding.textView.text = temp.toString()
        }

        binding.num8.setOnClickListener {
            temp = temp*10+8;
            binding.textView.text = temp.toString()
        }

        binding.num9.setOnClickListener {
            temp = temp*10+9;
            binding.textView.text = temp.toString()
        }

        binding.allClear.setOnClickListener {
            binding.textView.text =""
            binding.answer.text = ""
            ans=0.0
            temp=0.0
        }

        binding.divison.setOnClickListener {
            if(temp==0.0){
                binding.answer.text = "Division By Zero Error"
            }
            else{
                ans /= temp
                temp =0.0
                binding.answer.text = ans.toString()
                binding.textView.text = ""
            }
        }

        binding.multiply.setOnClickListener {
            ans *=temp
            temp=0.0
            binding.answer.text = ans.toString()
            binding.textView.text = ""

        }

        binding.add.setOnClickListener {
            ans+=temp
            temp=0.0
            binding.answer.text = ans.toString()
            binding.textView.text = ""
        }
        binding.sub.setOnClickListener {
            ans-=temp
            temp=0.0
            binding.answer.text = ans.toString()
            binding.textView.text = ""
        }

        binding.result.setOnClickListener {
            temp=ans
            ans=0.0
            binding.textView.text = "=$temp"
        }

        binding.sqrt.setOnClickListener {
            temp = sqrt(temp)
            binding.textView.text = "$temp"
        }

        binding.seq.setOnClickListener {
            temp *= temp
            binding.textView.text = "$temp"
        }

    }
}