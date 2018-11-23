package meter.tracking.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single
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
    fun getAll(): Single<List<Metric>>

    @Query("SELECT id, name, value, measureLabel, historyFrequency FROM metric WHERE id = :id")
    fun get(id: Long): Maybe<MetricsWithRecord>

    @Insert
    fun insert(m: Metric): Long

    @Insert
    fun insertAll(m: Array<Metric>): List<Long>

}