package meter.tracking.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import meter.tracking.db.model.MeasuringTypeEntity

/**
 * Dao to access [MeasuringTypeEntity] data
 * Use room engine
 * @author tweissbeck
 * @since 1.0.0
 */
@Dao
interface MeasuringTypeDao {

    @Query("SELECT label, decimal, unitSymbol FROM MeasuringTypeEntity")
    fun getAll(): LiveData<List<MeasuringTypeEntity>>

    @Insert
    fun insert(tuple: MeasuringTypeEntity): Long

    @Insert
    fun insertAll(tuples: Array<MeasuringTypeEntity>): List<Long>

    @Query("Delete from MeasuringTypeEntity")
    fun deleteAll()
}