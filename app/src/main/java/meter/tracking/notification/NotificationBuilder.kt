package meter.tracking.notification

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import meter.tracking.R
import meter.tracking.db.model.Metric

/**
 * A helper to build notifications
 * @author tweissbeck
 * @since 1.0.0
 */
object NotificationBuilder {

    fun buildReminderNotification(context: Context, channel: String, metric: Metric): Notification {
        return NotificationCompat.Builder(context, channel)
                //.setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(
                        context.getString(R.string.notification_metric_to_fill_remind_title, metric.measureLabel))
                .setContentText(context.getString(R.string.notification_metric_to_fill_remind_text, metric.measureLabel,
                                                  metric.historyFrequency.getKey()))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

    }

}