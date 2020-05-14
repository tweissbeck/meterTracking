package meter.tracking.repository

import androidx.lifecycle.LiveData
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.MetricEntity
import meter.tracking.db.model.MetricWithRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricRepository(private val metricDao: MetricDao) {

    fun init() {
        TODO("Not yet implemented")
    }

    fun getMetrics(): LiveData<List<MetricEntity>> {
        return metricDao.getAll();
    }

    fun getMetric(id: Long): LiveData<MetricWithRecord> {
        return metricDao.get(id)
    }

    fun saveMetric(metric: MetricEntity): Long {
        return metricDao.insert(metric);
    }

    fun saveMetrics(metrics: Array<MetricEntity>): List<Long> {
        return metricDao.insertAll(metrics);
    }
}