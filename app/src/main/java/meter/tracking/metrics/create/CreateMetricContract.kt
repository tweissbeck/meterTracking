package meter.tracking.metrics.create

import meter.tracking.BaseView
import meter.tracking.db.model.HistoryFrequency

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface CreateMetricContract {

    interface View : BaseView<Presenter> {
        fun navigateToMetricsListWithNewMetricAdded(metricName: String)
        fun navigateToMetricsListWithError(error: String)
        fun displayEmptyNameError()
        fun displayEmptyFrequency()
        fun displayEmptyUnit()
        fun navigateToMetricsList()
    }

    interface Presenter{
        fun createMetric(name: String?, historyFrequency: HistoryFrequency?, unit: String?)
    }
}