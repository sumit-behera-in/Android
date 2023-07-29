package com.example.phonepayui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigationView:BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController =findNavController(R.id.myfrag)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.historyFragment,R.id.storesFragment,R.id.welthFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}