package meter.tracking.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Android implementation of [SchedulerProvider]
 * @author tweissbeck
 * @since 1.0.0
 */
class AndroidSchedulerProvider : SchedulerProvider {
    /**
     *  @see AndroidSchedulers.mainThread
     */
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    /**
     * @see Schedulers.io
     */
    override fun io(): Scheduler = Schedulers.io()
}