package meter.tracking.metrics

import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricRepository(private val metricDao: MetricDao) : MetricDataSource {

    private lateinit var metrics: List<Metric>

    override fun init() {
        this.metrics = metricDao.getAll()
    }

    override fun getMetrics(): List<Metric> {
        // return metrics from local cache
        return this.metrics
    }

    override fun getMetric(id: Long): MetricsWithRecord? = metricDao.get(id)

}