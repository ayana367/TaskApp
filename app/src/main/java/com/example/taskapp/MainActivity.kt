package com.example.taskapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.ui.utils.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.newTaskFragment,
                R.id.navigation_profile,
                R.id.authFragment
            )
        )

      //  navController.navigate(R.id.authFragment)

         if(Preferences(applicationContext).isBoardingShowed())
            navController.navigate(R.id.navigation_home)
        else navController.navigate(R.id.onBoardFragment)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val list = setOf( R.id.newTaskFragment,R.id.onBoardFragment,R.id.authFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (list.contains(destination.id)){
                if (list.contains(destination.id)) {
                    navView.visibility = View.GONE
                } else navView.visibility = View.VISIBLE
                if (list.contains(destination.id)) {
                    supportActionBar?.hide()
                }
            }
        }
    }
}