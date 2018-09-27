package meter.tracking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metricRecord",
        foreignKeys = [(ForeignKey(entity = Metric::class, parentColumns = arrayOf("id"),
                                   childColumns = arrayOf("metric")))])
class MetricRecord(@PrimaryKey val id: Long, val metric: Long, val value: Long, val date: String)