package meter.tracking.di

import androidx.room.Room
import meter.tracking.db.MetricsDataBase
import meter.tracking.db.dao.MetricDao
import meter.tracking.metrics.main.MetricRepository
import org.koin.dsl.module.module

/**
 * @author tweissbeck
 * @since 1.0.0
 */
object ApplicationModule {
    val appModule = module {
        single { Room.databaseBuilder(get(), MetricsDataBase::class.java, "metric_base").build() }
        single { MetricRepository(getMetricDAO(get())) }
    }

    private fun getMetricDAO(db: MetricsDataBase): MetricDao = db.metricDao
}