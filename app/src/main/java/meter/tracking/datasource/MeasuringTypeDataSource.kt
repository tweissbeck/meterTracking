package meter.tracking.datasource

import io.reactivex.Single
import meter.tracking.db.model.MeasuringType

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MeasuringTypeDataSource {

    fun getAll(): Single<List<MeasuringType>>


    fun insert(tuple: MeasuringType): Single<Long>


    fun insertAll(tuples: Array<MeasuringType>): Single<List<Long>>
}