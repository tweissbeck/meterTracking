package meter.tracking.db.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import meter.tracking.db.model.MetricEntity
import meter.tracking.db.model.MetricWithRecord


/**
 * The interface to access metrics data.
 * This interface is annotate to be used by Room engine
 * @author tweissbeck
 * @since 1.0.0
 */
@Dao
interface MetricDao {

    @Query("SELECT id, name, value, measureLabel, historyFrequency, startDate FROM metric")
    fun getAll(): LiveData<List<MetricEntity>>

    @Transaction
    @Query("SELECT * FROM metric WHERE id = :id")
    fun get(id: Long): LiveData<MetricWithRecord>

    @Insert
    fun insert(m: MetricEntity): Long

    @Insert
    fun insertAll(m: Array<MetricEntity>): List<Long>

    @Query("Delete from metric")
    fun deleteAll()

}