package com.example.mvvmex1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmex1.R
import com.example.mvvmex1.fragments.CategoryFragment
import com.example.mvvmex1.fragments.FavoritesFragment
import com.example.mvvmex1.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView :BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = findNavController(R.id.myHost)
        bottomNavigationView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.myHost).navigateUp() ||super.onSupportNavigateUp()
    }


}