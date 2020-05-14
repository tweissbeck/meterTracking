package meter.tracking.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.preference.PreferenceManager
import androidx.work.Operation
import androidx.work.WorkManager
import meter.tracking.ui.job.PeriodicWorkRequestBuilder
import meter.tracking.ui.settings.SettingsKeys

/**
 * This BroadcastReceiver restart alarm on device reboot.
 * <i>By default, all alarms are canceled when a device shuts down.</i>
 * @author tweissbeck
 * @since 1.0.0
 */
class NotificationsBootReceiver : BroadcastReceiver() {

    private val TAG = "NotificationsBootReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED" ||
                intent?.action == "android.intent.action.QUICKBOOT_POWERON") {
            if (context != null) {
                val notificationEnabled = PreferenceManager.getDefaultSharedPreferences(context)
                        .getBoolean(SettingsKeys.NOTIFICATION_SETTINGS, false)
                if (notificationEnabled) {
                    Log.i(TAG, "Reset CheckNotCompletedCounterRequest in WorkManager")
                    val operation: Operation = WorkManager.getInstance().enqueue(
                            PeriodicWorkRequestBuilder.buildCheckNotCompletedCounterRequest())
                } else {
                    Log.d(TAG, "Notification is not enabled by client, don't setup work manager with our async jobs")
                }
            }


        }
    }
}