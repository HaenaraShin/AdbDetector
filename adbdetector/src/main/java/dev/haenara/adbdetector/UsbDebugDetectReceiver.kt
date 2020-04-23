package dev.haenara.adbdetector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * ADB 디버깅을 방지하기 위한 클래스
 * release 빌드에서 USB가 연결되고 개발자모드의 USB디버깅(ADB)이 허용되어 있으면
 * 앱을 강제로 종료하는 리시버 클래스
 */
class UsbDebugDetectReceiver(private val listener: OnAdbUsbListener? = null)
    : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val adbEnabled = AdbDetector(context).checkUsbDebuggingMode()
        intent.extras?.let {
            if (it.getBoolean("connected") && adbEnabled) {
                if (!isConnected) {
                    isConnected = true
                    val onDebugListener = listener ?: defaultListener
                    onDebugListener.onAdbUsbConnected(context)
                }
            } else {
                if (isConnected) {
                    isConnected = false
                    val onDebugListener = listener ?: defaultListener
                    onDebugListener.onAdbUsbDisconnected(context)
                }
            }
        }
    }

    private val defaultListener get() = object : OnAdbUsbListener{
        override fun onAdbUsbConnected(context: Context) {
            context.startActivity(Intent(context, EmptyActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
        override fun onAdbUsbDisconnected(context: Context) { }
    }

    companion object {
    // USB 연결 시,
    // 약 1초가 조금 넘는 간격으로 USB_STATE event가 2번 연속으로 들어오는 경우를 대비
    // 두번 연속 같은 이벤트가 들어오면서, 앱 실행 중단 안내 팝업이 2번 노출되는 경우가 발생.
    // 이를 대비하여, 최초 1번만 노출시키기 위해 체크 하는 용도
        private var isConnected = false
    }
}

