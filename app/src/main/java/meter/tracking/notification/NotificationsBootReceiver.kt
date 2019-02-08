package meter.tracking.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Operation
import androidx.work.WorkManager
import meter.tracking.job.PeriodicWorkRequestBuilder

/**
 * This BroadcastReceiver restart alarm on device reboot.
 * <i>By default, all alarms are canceled when a device shuts down.</i>
 * @author tweissbeck
 * @since 1.0.0
 */
class NotificationsBootReceiver : BroadcastReceiver() {

    private val TAG = "NotificationsBootReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED" || intent?.action == "android.intent.action.QUICKBOOT_POWERON") {
            Log.i(TAG, "Reset CheckNotCompletedCounterRequest in WorkManager")
            val operation: Operation = WorkManager.getInstance().enqueue(
                    PeriodicWorkRequestBuilder.buildCheckNotCompletedCounterRequest())

        }
    }
}