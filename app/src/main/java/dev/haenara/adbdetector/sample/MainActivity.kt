package dev.haenara.adbdetector.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.haenara.adbdetector.AdbDetector

class MainActivity : AppCompatActivity() {

    private lateinit var tvState: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvState = findViewById(R.id.tvState)

        findViewById<Button>(R.id.btnDefault).setOnClickListener {
            AdbDetector(this).registerAdbDetectReceiver()
        }

        findViewById<Button>(R.id.btnCustom).setOnClickListener {
            val detector = AdbDetector(this)
            Toast.makeText(
                this,
                "debug: ${detector.isDebugEnabled()},\nusb: ${detector.isUsbConnected()}",
                Toast.LENGTH_SHORT
            ).show()

            detector.registerAdbDetectReceiver(
                {
                    tvState.text = "Connected"
                }, {
                    tvState.text = "Disconnected"
                }
            )
        }
    }


}
