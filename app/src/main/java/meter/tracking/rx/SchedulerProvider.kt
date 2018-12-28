package meter.tracking.rx

import io.reactivex.Scheduler

/**
 * A simple [Scheduler] provider for java rx use in [meter.tracking.BasePresenter]
 * @author tweissbeck
 * @since 1.0.0
 */
interface SchedulerProvider {

    fun ui(): Scheduler
    fun io(): Scheduler
}