package meter.tracking.metrics

import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricRepository(private val metricDao: MetricDao) : MetricDataSource {


    private var metrics: List<Metric>? = null

    override fun getMetrics(): List<Metric> {
        return if (metrics == null) {
            this.metrics = metricDao.getAll()
            this.metrics!!
        } else {
            this.metrics!!
        }
    }

    override fun getMetric(id: Long): MetricsWithRecord? = metricDao.get(id)

}