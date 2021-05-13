package com.example.gazeteretrofit.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gazeteretrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
   lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var navController=Navigation.findNavController(this,R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        sharedPref = this?.getPreferences(Context.MODE_PRIVATE)!!
    }
    override fun onStop() {
        sharedPref.edit().putString("key","").apply()
        super.onStop()
    }

    override fun onDestroy() {
        sharedPref.edit().putString("key","").apply()
        super.onDestroy()
    }




}