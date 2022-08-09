package com.example.moviestask.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.example.moviestask.R
import com.example.moviestask.databinding.ActivityMainBinding
import com.example.moviestask.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mController: NavController
    /**
     *  Movies View Model
     */
    private val mMoviesViewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.nav_host_fragment)
        //connect action bar with navigation controller

        NavigationUI.setupActionBarWithNavController(this,navController)
    }
    //navigate up when click on navigate up icon
    override fun onSupportNavigateUp():Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigateUp()
        return true
    }
}