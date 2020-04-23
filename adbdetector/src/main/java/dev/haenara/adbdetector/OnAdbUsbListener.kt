package dev.haenara.adbdetector

import android.content.Context

interface OnAdbUsbListener {
    /**
     * ADB USB가 연결 되었을 때 처리
     */
    fun onAdbUsbConnected(context : Context)
    /**
     * ADB USB가 분리 되었을 때 처리
     */
    fun onAdbUsbDisconnected(context : Context)
}