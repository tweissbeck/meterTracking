package meter.tracking.job

import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit


/**
 * @author tweissbeck
 * @since 1.0.0
 */
object PeriodicWorkRequestBuilder {

    const val NotCompletedCounterWorkTag: String = "NotCompletedCounterWorkTag"

    fun buildCheckNotCompletedCounterRequest(): PeriodicWorkRequest {
        return PeriodicWorkRequest.Builder(CheckNotCompletedCounterWorker::class.java, 12,
                                           TimeUnit.HOURS)
                .addTag(NotCompletedCounterWorkTag)

                .build()
    }
}