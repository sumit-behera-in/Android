package com.example.alarmapp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import com.example.alarmapp.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private val calender:Calendar = Calendar.getInstance()
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        binding.setAlarm.setOnClickListener {
            setAlarm()
        }

        binding.selectTime.setOnClickListener {

            showTimerPicker()
        }
        binding.cancelAlarm.setOnClickListener {
            cancelAlarm()
        }

    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this,AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(this,"Alarm Cancelled",Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this,AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,calender.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )

        Toast.makeText(this,"Alarm Successful",Toast.LENGTH_SHORT).show()
    }

    private fun showTimerPicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(supportFragmentManager,"MYAPP")

        picker.addOnPositiveButtonClickListener {
            if(picker.hour>12){
                binding.time.text =
                    String.format("%02d",picker.hour-12) + ":" + String.format("%02d",picker.minute) + "PM"
            }
            else{
                binding.time.text =
                    String.format("%02d",picker.hour) + ":" + String.format("%02d",picker.minute) + "AM"
            }
            calender[Calendar.HOUR_OF_DAY]=picker.hour
            calender[Calendar.MINUTE] = picker.minute
            calender[Calendar.SECOND] =0
            calender[Calendar.MILLISECOND]=0
        }
    }

    private fun createNotificationChannel() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name :CharSequence = "Hi My Alarm App"
                val description  = "Alarm Manager Rang"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel("MYAPP",name,importance)
                channel.description = description
                val notificationManager = getSystemService(
                    NotificationManager::class.java
                )
                notificationManager.createNotificationChannel(channel)

//        }
    }
}