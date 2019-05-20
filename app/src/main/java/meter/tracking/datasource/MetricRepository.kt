package meter.tracking.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord
import meter.tracking.metrics.create.MetricDTO

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricRepository(private val metricDao: MetricDao) : MetricDataSource {
    override fun saveMetric(dto: MetricDTO): Single<Long> {
        return metricDao.insert(Metric(dto.name, 0L, dto.unit, dto.type, dto.startDate))
    }

    override fun saveMetrics(metrics: Array<Metric>): Single<List<Long>> {
        return metricDao.insertAll(metrics)
    }

    override fun init() {

    }

    override fun getMetrics(): Single<List<Metric>> {
        return metricDao.getAll()
    }

    override fun getMetric(id: Long): Maybe<MetricsWithRecord> = metricDao.get(id)

}