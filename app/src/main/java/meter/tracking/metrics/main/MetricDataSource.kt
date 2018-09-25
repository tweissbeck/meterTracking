package meter.tracking.metrics.main

import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricDataSource {

    /**
     * A hook if some stuff has to be done when application is launching. Eg: put in cache some data
     */
    fun init()

    fun getMetrics(): List<Metric>

    fun getMetric(id: Long): MetricsWithRecord?
}