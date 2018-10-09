package meter.tracking.metrics.main

import io.reactivex.Maybe
import io.reactivex.Single
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

    override fun getMetrics(): Single<List<Metric>> {
        return metricDao.getAll().map { collection ->
            collection + listOf(Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY),
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY),
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY),
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY),
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY),
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY),
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY),
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY),
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY),
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY),
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY))
        }
    }

    override fun getMetric(id: Long): Maybe<MetricsWithRecord> = metricDao.get(id)

}