package dev.haenara.adbdetector.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.haenara.adbdetector.AdbDetector

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AdbDetector(this).registerAdbDetectReceiver()
    }
}
