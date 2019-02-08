package meter.tracking.rx

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Android implementation of [SchedulerProvider]
 * @author tweissbeck
 * @since 1.0.0
 */
class AndroidSchedulerProvider(context: Context): SchedulerProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()
}