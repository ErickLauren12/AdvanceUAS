package com.example.a160419095_advancenativeuts.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.a160419095_advancenativeuts.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    //Untuk sensor cahaya
    private var lightSensor: Sensor? = null
    private var lightReading = 0f
    private lateinit var  sensorManager: SensorManager
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //Sensor Cahaya
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        var actionBar = supportActionBar
        if(actionBar!=null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
        bottomNav.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        //cek sensor cahaya
        if(lightSensor == null){
            Toast.makeText(this, "No Light sensor Detected",
                Toast.LENGTH_SHORT).show()
        }
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST)
        //Toast.makeText(this, "Light Sensor detected",Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let {
            when(it.sensor.type){

                Sensor.TYPE_LIGHT->{
                    lightReading = it.values[0]

                    if(lightReading <=10){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}