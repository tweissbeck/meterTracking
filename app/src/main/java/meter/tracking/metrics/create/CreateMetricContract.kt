package meter.tracking.metrics.create

import meter.tracking.BaseView

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface CreateMetricContract {

    interface View : BaseView<Presenter> {
        fun showMetricListWithResult(message: String)
        fun showMetricListWithError(error: String)
    }

    interface Presenter{
        fun createMetric(m:MetricDTO)
    }
}