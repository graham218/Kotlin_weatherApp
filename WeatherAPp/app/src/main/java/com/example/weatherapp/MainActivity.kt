package com.example.weatherapp

import android.os.AsyncTask
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.weatherapp.databinding.ActivityMainBinding
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

    val CITY: String="dhaka, bd"
    val API: String="521ae0e22dca7a1dc71169c4d4154476"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherTask().execute()
    }
    inner class weatherTask(): AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility= View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility= View.GONE
            findViewById<TextView>(R.id.errortext).visibility= View.GONE
        }
        override fun doInBackground(vararg p0: String?): String? {
            var response:String?
            try {
                response= URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(Charsets.UTF_8)

            }
            catch (e: Exception){
                response=null
            }
            return response
        }
    }
}