package meter.tracking.db.dao

import androidx.room.Dao
import androidx.room.Insert
import meter.tracking.db.model.MetricRecordEntity

@Dao
interface RecordDao {

    @Insert
    fun insertRecord(record: MetricRecordEntity)
}