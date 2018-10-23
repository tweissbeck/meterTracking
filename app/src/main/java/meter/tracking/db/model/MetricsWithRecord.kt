package meter.tracking.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metric")
class MetricsWithRecord(@PrimaryKey val id: Long, val name: String, val value: Long, @ColumnInfo(
        name = "measureLabel") val measureLabel: String,
                        val historyFrequency: HistoryFrequency) {
    @Relation(parentColumn = "id", entityColumn = "metric")
    lateinit var records: List<MetricRecord>

    override fun toString(): String {
        return "id:$id, name:$name, value:$value"
    }
}