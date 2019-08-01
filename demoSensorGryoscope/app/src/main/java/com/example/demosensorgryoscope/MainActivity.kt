package com.example.demosensorgryoscope

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

        lateinit var sensorManager : SensorManager
        var kq : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        val gyroscopeSensorListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                val x = sensorEvent.values[0]
                val y = sensorEvent.values[1]
                val z = sensorEvent.values[2]


                tv_x.text = "X = $x"
                tv_y.text = "Y = $y"
                tv_z.text = "Z = $z"

                when {
                    sensorEvent.values[0] > 2f -> {
                        window.decorView.setBackgroundColor(Color.BLUE)
                        kq++
                    }
                    sensorEvent.values[0] < -2f ->{
                        window.decorView.setBackgroundColor(Color.YELLOW)
                        kq++
                    }
                    sensorEvent.values[1] > 1f ->
                        window.decorView.setBackgroundColor(Color.GREEN)
                    sensorEvent.values[1] < -1f ->
                        window.decorView.setBackgroundColor(Color.RED)
                    sensorEvent.values[2] > 1f ->
                        window.decorView.setBackgroundColor(Color.WHITE)
                    sensorEvent.values[2] < -1f ->
                        window.decorView.setBackgroundColor(Color.GRAY)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                tv_kq.text = kq.toString()
                mainHandler.postDelayed(this, 2000)
            }
        })

// Register the listener
        sensorManager.registerListener(
            gyroscopeSensorListener,
            gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL
        )
    }
}
