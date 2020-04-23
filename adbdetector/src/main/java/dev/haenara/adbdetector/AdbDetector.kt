package dev.haenara.adbdetector

import android.annotation.TargetApi
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.provider.Settings

class AdbDetector(private val mContext : Context) {
    /**
     * USB연결을 감지하는 필터를 등록하고
     */
    fun registerAdbDetectReceiver(listener: OnAdbDebugListener? = null) {
        // USB 연결을 감지하는 필터를 등록
        val filter = IntentFilter()
        filter.addAction("android.hardware.usb.action.USB_STATE")
        mContext.registerReceiver(UsbDebugDetectReceiver(listener), filter)
    }
    /**
    * USB연결 정책 위반 여부 확인
    * USB 연결 여부, USB디버깅설정 여부 모두 true 면 true
    * @return
    */
    fun checkUsbDebuggingMode(): Boolean {
        return isDebugEnable() and isUsbConnected()
    }

    /**
     * 개발자모드에서 USB디버깅이 허용되어 있는지 확인
     * @param context
     * @return
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
     * USB 연결이 되어있는지 확인
     * @param context
     * @return
     */
    private fun isUsbConnected(): Boolean {
        val intent =
            mContext.registerReceiver(null, IntentFilter("android.hardware.usb.action.USB_STATE"))
        return intent != null && intent.extras != null &&
            intent.extras!!.getBoolean("connected")
    }
}