package meter.tracking.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import meter.tracking.datasource.MeasuringTypeDataSource
import meter.tracking.db.model.MeasuringType

/**
 * Dao to access [MeasuringType] data
 * Use room engine
 * @author tweissbeck
 * @since 1.0.0
 */
@Dao
interface MeasuringTypeDao {

    @Query("SELECT label, decimal, unitSymbol FROM MeasuringType")
    fun getAll(): Single<List<MeasuringType>>

    @Insert
    fun insert(tuple: MeasuringType): Single<Long>

    @Insert
    fun insertAll(tuples: Array<MeasuringType>): Single<List<Long>>
}