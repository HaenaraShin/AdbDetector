package dev.haenara.adbdetector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * A BroadcastReceiver to check if USB Debugging is enabled and usb is connected dynamically.
 */
class UsbDebugDetectReceiver(private val listener: OnAdbUsbListener? = null) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        intent.extras?.let {
            val cb = listener ?: defaultListener
            if (AdbDetector(context).checkUsbDebuggingMode()) {
                cb.onAdbUsbConnected(context)
            } else {
                cb.onAdbUsbDisconnected(context)
            }
        }
    }

    /**
     * As a default, launch an empty activity with a simple dialog
     * that kills application process.
     */
    private val defaultListener
        get() = object : OnAdbUsbListener {
            override fun onAdbUsbConnected(context: Context) {
                context.startActivity(Intent(context, EmptyActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }

            override fun onAdbUsbDisconnected(context: Context) {}
        }

    companion object {
        // To prevent duplicated event handling
        private var isConnected = false
    }
}