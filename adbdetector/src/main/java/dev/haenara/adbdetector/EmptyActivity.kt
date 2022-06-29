package dev.haenara.adbdetector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess

/**
 * An empty activity with a simple dialog
 * that kills application process.
 */
class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.adb_detect_default_error_title)
            .setMessage(R.string.adb_detect_default_error_message)
            .setPositiveButton(R.string.adb_detect_default_error_confirm) { _, _ ->
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(-1)
            }
            .create()
            .show()
    }
}
