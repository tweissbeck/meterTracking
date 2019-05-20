package meter.tracking.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
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

    @Query("SELECT id, name, value, measureLabel, historyFrequency, startDate FROM metric")
    fun getAll(): Single<List<Metric>>

    @Transaction
    @Query("SELECT id, name, value, measureLabel, historyFrequency, startDate FROM metric WHERE id = :id")
    fun get(id: Long): Maybe<MetricsWithRecord>

    @Insert
    fun insert(m: Metric): Single<Long>

    @Insert
    fun insertAll(m: Array<Metric>): Single<List<Long>>

}