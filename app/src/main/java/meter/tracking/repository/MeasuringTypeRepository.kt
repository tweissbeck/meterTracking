package meter.tracking.repository

import androidx.lifecycle.LiveData
import io.reactivex.Single
import meter.tracking.db.dao.MeasuringTypeDao
import meter.tracking.db.model.MeasuringTypeEntity

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MeasuringTypeRepository(private val measuringTypeDao: MeasuringTypeDao) {
    fun getAll(): LiveData<List<MeasuringTypeEntity>> = measuringTypeDao.getAll()

    fun insert(tuple: MeasuringTypeEntity): Long = this.measuringTypeDao.insert(tuple)

    fun insertAll(tuples: Array<MeasuringTypeEntity>): List<Long> =
            this.measuringTypeDao.insertAll(tuples)


}