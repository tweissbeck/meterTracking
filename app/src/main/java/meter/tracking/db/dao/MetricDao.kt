package meter.tracking.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord


/**
 * The interface to access metrics data.
 * This interface is annotate to be used by Room engine
 * @author tweissbeck
 * @since 1.0.0
 */
@Dao
interface MetricDao {

    @Query("SELECT id, name, value, measureLabel, historyFrequency FROM metric")
    fun getAll(): List<Metric>

    @Query("SELECT metric.id, metric.name, metric.value, metric.measureLabel, historyFrequency FROM metric, metricRecord WHERE metric.id = :id")
    fun get(id: Long): MetricsWithRecord?

    @Insert
    fun insert(m: Metric)

}