package meter.tracking.metrics

import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricDataSource {

    fun getMetrics(): List<Metric>

    fun getMetric(id: Long): MetricsWithRecord?
}