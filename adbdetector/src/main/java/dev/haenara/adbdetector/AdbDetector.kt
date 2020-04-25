package dev.haenara.adbdetector

import android.annotation.TargetApi
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.provider.Settings

/**
 * ADB Detector can check USB Debugging is activated both dynamically and statically.
 * To check it dynamically, use a register a BroadcastReceiver.
 * To check it statically, use checkUsbDebuggingMode function.
 */
class AdbDetector(private val mContext : Context) {
    /**
     * Register an ADB-detect receiver,so that checks
     * if USB Debugging is enabled and usb is connected dynamically.
     */
    fun registerAdbDetectReceiver(listener: OnAdbUsbListener? = null) {
        // USB 연결을 감지하는 필터를 등록
        val filter = IntentFilter()
        filter.addAction("android.hardware.usb.action.USB_STATE")
        val receiver = UsbDebugDetectReceiver(listener)
        mContext.registerReceiver(receiver, filter)
        lastListener = receiver
    }

    /**
     * Unregister the last Adb detect receiver.
     */
    fun unregisterAdbDetectReceiver() {
        // USB 연결을 감지하는 필터를 등록
        lastListener?.let{
            mContext.unregisterReceiver(lastListener)
        }
    }


    /**
    * Check if USB Debugging is enabled and usb is connected statically.
    */
    fun checkUsbDebuggingMode(): Boolean {
        return isDebugEnable() and isUsbConnected()
    }

    /**
     * Check if USB Debugging is enabled.
     */
    @TargetApi(17)
    fun isDebugEnable(): Boolean {
        return when {
            Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN -> Settings.Secure.getInt(
                mContext.contentResolver,
                Settings.Secure.ADB_ENABLED, 0
            ) != 0
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> Settings.Secure.getInt(
                mContext.contentResolver,
                Settings.Global.ADB_ENABLED, 0
            ) != 0
            else -> false
        }
    }

    /**
     * Check if USB is connected statically.
     */
    private fun isUsbConnected(): Boolean {
        val intent = mContext.registerReceiver(
            null,
            IntentFilter("android.hardware.usb.action.USB_STATE")
        )
        return intent != null && intent.extras != null &&
                intent.extras!!.getBoolean("connected")
    }

    companion object {
        var lastListener : UsbDebugDetectReceiver? = null
    }
}