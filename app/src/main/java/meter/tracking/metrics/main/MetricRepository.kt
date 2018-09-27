package meter.tracking.metrics.main

import android.os.AsyncTask
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord
import meter.tracking.metrics.create.MetricDTO

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricRepository(private val metricDao: MetricDao) : MetricDataSource {
    override fun saveMetric(dto: MetricDTO, callback: MetricDataSource.SaveMetricCallBack) {

    }

    override fun init() {

    }

    private var metrics: List<Metric>? = null

    override fun getMetrics(): List<Metric> {
        return if (metrics == null) {
            this.metrics = metricDao.getAll() + listOf(Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                                       Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY),
                                                       Metric("Water", 1420, "L", HistoryFrequency.MONTHLY),
                                                       Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY),
                                                       Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY))
            this.metrics!!
        } else {
            this.metrics!!
        }
    }

    override fun getMetric(id: Long): MetricsWithRecord? = metricDao.get(id)

}