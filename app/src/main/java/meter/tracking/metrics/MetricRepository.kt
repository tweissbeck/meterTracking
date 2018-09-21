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
            this.metrics = metricDao.getAll() + listOf(Metric(1, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(2, "CAR2", 15874, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(3, "Water", 1420, "L", HistoryFrequency.MONTHLY),
                                                       Metric(4, "Elec", 142078, "Watt", HistoryFrequency.MONTHLY),
                                                       Metric(5, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(6, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(8, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(9, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(10, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric(11, "CAR1", 1420, "Km", HistoryFrequency.MONTHLY))
            this.metrics!!
        } else {
            this.metrics!!
        }
    }

    override fun getMetric(id: Long): MetricsWithRecord? = metricDao.get(id)

}