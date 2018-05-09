package meter.tracking.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import meter.tracking.db.converter.HistoryTypeConverter
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.model.MeasuringType
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricRecord


/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Database(entities = [(Metric::class), (MetricRecord::class), (MeasuringType::class)], version = 1)
@TypeConverters(HistoryTypeConverter::class)
abstract class MetricsDataBase : RoomDatabase() {
    abstract val metricDao: MetricDao
}