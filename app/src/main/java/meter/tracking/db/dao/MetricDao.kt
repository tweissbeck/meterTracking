package meter.tracking.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
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

    @Query("SELECT id, value, measureLabel, historyFrequency FROM metric")
    fun getAll(): List<Metric>

    @Query("SELECT id, value, measureLabel, historyFrequency FROM metric, metricRecord")
    fun get(id: Long): MetricsWithRecord?

}