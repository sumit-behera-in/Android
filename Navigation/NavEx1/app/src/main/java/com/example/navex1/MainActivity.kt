package com.example.navex1

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

    private lateinit var listner: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = this.findNavController(R.id.fragmentContainerView)
        drawerLayout = findViewById(R.id.drawerlayout)


        val navigationView:NavigationView = findViewById(R.id.navigationView)
        navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        listner = NavController.OnDestinationChangedListener{controller, destination, arguments ->
            if(destination.id==R.id.fragment1){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(androidx.appcompat.R.color.dim_foreground_material_dark)))
            }
            else if (destination.id==R.id.fragment2){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_200)))

            }
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