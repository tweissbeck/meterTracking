package meter.tracking.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * This BroadcastReceiver restart alarm on device reboot.
 * <i>By default, all alarms are canceled when a device shuts down.</i>
 * @author tweissbeck
 * @since 1.0.0
 */
class NotificationsBootReceiver: BroadcastReceiver() {



    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            // TODO restart alarm configured into data base by user
        }
    }
}