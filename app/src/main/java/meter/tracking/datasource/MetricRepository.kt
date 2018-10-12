package meter.tracking.datasource

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
            var id = 0L
            collection + listOf(Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 2", 15874, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Water", 1420, "L", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                                Metric("CAR 1", 1420, "Km", HistoryFrequency.MONTHLY).apply { this.id = id++ })
        }
    }

    override fun getMetric(id: Long): Maybe<MetricsWithRecord> = metricDao.get(id)

}