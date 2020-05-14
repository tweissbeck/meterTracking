package meter.tracking.ui.notification

import android.content.Context
import androidx.core.app.NotificationCompat
import meter.tracking.R
import meter.tracking.db.model.MetricEntity

/**
 * A helper to build notifications
 * @author tweissbeck
 * @since 1.0.0
 */
object NotificationBuilder {

    /**
     * Build a notification for a missing metric.
     */
    fun buildReminderNotification(context: Context, channel: String, metric: MetricEntity): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channel)
                .setSmallIcon(R.drawable.ic_notification_small) // mandatory
                .setContentTitle(
                        context.getString(R.string.notification_metric_to_fill_remind_title, metric.measureLabel))
                .setContentText(context.getString(R.string.notification_metric_to_fill_remind_text, metric.measureLabel,
                        metric.historyFrequency.getKey()))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)

    }

}