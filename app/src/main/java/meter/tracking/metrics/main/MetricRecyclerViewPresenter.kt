package meter.tracking.metrics.main

import meter.tracking.db.model.Metric

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricRecyclerViewPresenter {

    fun handleSelect(metric: Metric)
}