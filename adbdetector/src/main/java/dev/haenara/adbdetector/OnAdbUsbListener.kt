package dev.haenara.adbdetector

import android.content.Context

interface OnAdbUsbListener {
    /**
     * When USB debugging is enabled and usb is connected.
     */
    fun onAdbUsbConnected(context : Context)
    /**
     * When USB debugging is not enabled or usb is disconnected.
     */
    fun onAdbUsbDisconnected(context : Context)
}