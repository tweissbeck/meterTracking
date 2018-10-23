package meter.tracking.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import meter.tracking.db.converter.HistoryTypeConverter
import meter.tracking.db.dao.MeasuringTypeDao
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
    abstract val measuringType: MeasuringTypeDao
}