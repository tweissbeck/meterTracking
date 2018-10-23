package meter.tracking.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord
import meter.tracking.metrics.create.MetricDTO
import kotlin.math.sin

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricRepository(private val metricDao: MetricDao) : MetricDataSource {
    override fun saveMetric(dto: MetricDTO): Single<Long> {
        val singleEmitter = SingleOnSubscribe<Long> {
            metricDao.insert(Metric(dto.name, 0, dto.unit, HistoryFrequency.MONTHLY))
        }
        return Single.create(singleEmitter)
        //return metricDao.insert(Metric(dto.name, 0, dto.unit, HistoryFrequency.MONTHLY))
    }

    override fun saveMetrics(metrics: Array<Metric>): Single<List<Long>> {
        val singleEmitter = SingleOnSubscribe<List<Long>> {
            metricDao.insertAll(metrics)
        }
        return Single.create(singleEmitter)
    }

    override fun init() {

    }

    override fun getMetrics(): Single<List<Metric>> {
        return metricDao.getAll()
    }

    override fun getMetric(id: Long): Maybe<MetricsWithRecord> = metricDao.get(id)

}