package meter.tracking.db.dao

import meter.tracking.db.model.MeasuringType

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MeasuringTypeDao {

    fun getAll(): List<MeasuringType>
}