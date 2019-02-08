package meter.tracking.job

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class CheckNotCompletedCounterWorker(context : Context, params : WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        // TODO step 1: check if one counter have a missing value at this day.
        // TODO step 2: send notification if needed

        return Result.success()
    }
}