package com.example.task

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.task.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val mycalender = Calendar.getInstance()
    private val datePicker = DatePickerDialog.OnDateSetListener{View,year,month,dayofmonth ->
        mycalender.set(Calendar.YEAR,year)
        mycalender.set(Calendar.MONTH,month)
        mycalender.set(Calendar.DAY_OF_MONTH,dayofmonth)
        updateLabel(mycalender)
    }

    private fun updateLabel(mycalender: Calendar) {
        val myformat = "dd-mm-yyyy"
        val sdf = SimpleDateFormat(myformat, Locale.UK)
        binding.editDOB.setText(sdf.format(mycalender.time))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //below line is very very important
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.string=Mystrings()
        //above line is very very important


        binding.editDOB.setOnClickListener{
                DatePickerDialog(this,datePicker,mycalender.get(Calendar.YEAR),mycalender.get(Calendar.MONTH),mycalender.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.submit.setOnClickListener {
            binding.heading.text = "You have sucessfully Registered"

            binding.txtName.text = binding.editName.text
            binding.txtName.visibility= View.VISIBLE
            binding.editName.visibility=View.GONE

            binding.txtDOB.text=binding.editDOB.text
            binding.txtDOB.visibility=View.VISIBLE
            binding.editDOB.visibility=View.GONE

            binding.txtMobile.text=binding.editMobile.text
            binding.txtMobile.visibility=View.VISIBLE
            binding.editMobile.visibility=View.GONE

            Toast.makeText(applicationContext,"Competed",Toast.LENGTH_SHORT).show()
        }
    }
}