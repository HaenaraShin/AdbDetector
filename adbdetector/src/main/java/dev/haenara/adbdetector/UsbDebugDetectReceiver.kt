package dev.haenara.adbdetector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * ADB 디버깅을 방지하기 위한 클래스
 * release 빌드에서 USB가 연결되고 개발자모드의 USB디버깅(ADB)이 허용되어 있으면
 * 앱을 강제로 종료하는 리시버 클래스
 */
class UsbDebugDetectReceiver : BroadcastReceiver() {
    private var lastDectedTime: Long = 0
    override fun onReceive(context: Context, intent: Intent) {
        val adbEnabled = AdbDetector(context).checkUSBDebugingMode()
        intent.extras?.let {
            if (it.getBoolean("connected") && adbEnabled) {
                val currentDectedTime = System.currentTimeMillis()
                if (currentDectedTime - lastDectedTime > USB_DECTECTED_IGNORE_TERM) {
                    lastDectedTime = currentDectedTime
                    val intent = Intent(context, EmptyActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                } else {
                    lastDectedTime = currentDectedTime
                }
            }
        }
    }

    companion object {
    // USB 연결 시,
    // 약 1초가 조금 넘는 간격으로 USB_STATE event가 2번 연속으로 들어오는 경우를 대비
    // 두번 연속 같은 이벤트가 들어오면서, 앱 실행 중단 안내 팝업이 2번 노출되는 경우가 발생.
    // 이를 대비하여, 최초 1번만 노출시키기 위해 체크 하는 용도
        private const val USB_DECTECTED_IGNORE_TERM: Long = 2000 //2초
    }
}