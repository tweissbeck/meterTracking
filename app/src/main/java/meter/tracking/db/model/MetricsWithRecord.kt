package meter.tracking.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metric")
class MetricsWithRecord(@PrimaryKey val id: Long, val name: String, val value: Long, @ColumnInfo(
        name = "measureLabel") val measureLabel: String,
                        val historyFrequency: HistoryFrequency){
    @android.arch.persistence.room.Relation(parentColumn = "id", entityColumn = "metric")
    lateinit var records: List<MetricRecord>
}