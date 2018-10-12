package meter.tracking.metrics.main

import meter.tracking.BaseView
import meter.tracking.db.model.Metric

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricMainContract {

    interface View : BaseView<Presenter> {
        fun navigateToEditMetricActivity(metric: Metric)
    }

    interface Presenter : MetricRecyclerViewPresenter
}