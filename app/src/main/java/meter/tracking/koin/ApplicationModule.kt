package meter.tracking.koin

import androidx.room.Room
import meter.tracking.repository.MeasuringTypeRepository
import meter.tracking.db.MetricsDataBase
import meter.tracking.db.dao.MetricDao
import meter.tracking.repository.MetricRepository
import meter.tracking.rx.AndroidSchedulerProvider
import meter.tracking.rx.SchedulerProvider
import org.koin.dsl.module


/**
 * Define KOIN module
 * @author tweissbeck
 * @since 1.0.0
 */
object ApplicationModule {
    val appModule = module {
        single { Room.databaseBuilder(get(), MetricsDataBase::class.java, "metric_base").build() }

        single<SchedulerProvider> { AndroidSchedulerProvider() }
    }

}