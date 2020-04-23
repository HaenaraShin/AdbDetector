package dev.haenara.adbdetector.sample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.haenara.adbdetector.AdbDetector
import dev.haenara.adbdetector.OnAdbUsbListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AdbDetector(this).registerAdbDetectReceiver()

        btnDefault.setOnClickListener {
            AdbDetector(this).unregisterAdbDetectReceiver()
            AdbDetector(this).registerAdbDetectReceiver()
        }
        btnCustom.setOnClickListener {
            AdbDetector(this).unregisterAdbDetectReceiver()
            AdbDetector(this).registerAdbDetectReceiver(object : OnAdbUsbListener{
                override fun onAdbUsbConnected(context: Context) {
                    tvState.text = "Connected"
                }

                override fun onAdbUsbDisconnected(context: Context) {
                    tvState.text = "Disconnected"
                }
            })
        }
    }


}
