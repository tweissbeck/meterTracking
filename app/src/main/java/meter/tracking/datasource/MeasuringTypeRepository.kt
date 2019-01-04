package meter.tracking.datasource

import io.reactivex.Single
import meter.tracking.db.dao.MeasuringTypeDao
import meter.tracking.db.model.MeasuringType

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MeasuringTypeRepository(private val measuringTypeDao: MeasuringTypeDao) : MeasuringTypeDataSource {
    override fun getAll(): Single<List<MeasuringType>> = measuringTypeDao.getAll()

    override fun insert(tuple: MeasuringType): Single<Long> = Single.create { this.measuringTypeDao.insert(tuple) }

    override fun insertAll(tuples: Array<MeasuringType>): Single<List<Long>> = Single.create {
        this.measuringTypeDao.insertAll(tuples)
    }

}