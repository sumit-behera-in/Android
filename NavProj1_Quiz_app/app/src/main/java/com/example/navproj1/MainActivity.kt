package com.example.navproj1

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var listner:NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragmentContainerView)
        drawerLayout = findViewById(R.id.drawerlayout)

        val navigationView =findViewById<NavigationView>(R.id.nav_view)
        navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        listner = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.fragment1)
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_200)))
            else if (destination.id == R.id.fragment2)
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_500)))
            else if (destination.id == R.id.fragment3)
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_700)))
            else if(destination.id==R.id.title)
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
            else
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_700)))
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listner)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listner)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
