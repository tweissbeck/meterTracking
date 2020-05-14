package meter.tracking.ui.job

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MetricEntity
import meter.tracking.ui.notification.ChannelID
import meter.tracking.ui.notification.NotificationBuilder
import org.koin.core.KoinComponent
import org.koin.core.inject


import java.time.LocalDate.now
import kotlin.random.Random

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class CheckNotCompletedCounterWorker(val context: Context, params: WorkerParameters) : Worker(context, params), KoinComponent {
    val a: MetricDao by inject()

    override fun doWork(): Result {

        // TODO step 1: check if one counter have a missing value at this day.


        // send notification if needed
        NotificationManagerCompat.from(context).apply {
            val builder = NotificationBuilder.buildReminderNotification(context, ChannelID.DEFAULT_CHANNEL_ID,
                    MetricEntity("", 0, "", HistoryFrequency.DAILY, now()))
            notify(Random.nextInt(), builder.build())
        }

        return Result.success()
    }
}