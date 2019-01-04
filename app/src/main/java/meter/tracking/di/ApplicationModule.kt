package meter.tracking.di

import androidx.room.Room
import meter.tracking.datasource.MeasuringTypeDataSource
import meter.tracking.db.MetricsDataBase
import meter.tracking.db.dao.MetricDao
import meter.tracking.datasource.MetricDataSource
import meter.tracking.datasource.MetricRepository
import meter.tracking.db.dao.MeasuringTypeDao
import meter.tracking.rx.AndroidSchedulerProvider
import meter.tracking.rx.SchedulerProvider
import org.koin.dsl.module.module

/**
 * @author tweissbeck
 * @since 1.0.0
 */
object ApplicationModule {
    val appModule = module {
        single { Room.databaseBuilder(get(), MetricsDataBase::class.java, "metric_base").build() }
        single<MetricDataSource> { MetricRepository(getMetricDAO(get())) }
        single<MeasuringTypeDataSource> { getMeasuringTypeDAO(get()) }
        single<SchedulerProvider> { AndroidSchedulerProvider(get()) }
    }

    private fun getMetricDAO(db: MetricsDataBase): MetricDao = db.metricDao
    private fun getMeasuringTypeDAO(db: MetricsDataBase): MeasuringTypeDao = db.measuringType
}