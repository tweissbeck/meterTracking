package meter.tracking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * @param id
 * @param metric
 * @param value
 * @param date
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metricRecord",
        foreignKeys = [(ForeignKey(entity = Metric::class, parentColumns = arrayOf("id"),
                                   childColumns = arrayOf("metric")))],
        indices = arrayOf(Index(value= ["metric"])))
class MetricRecord(@PrimaryKey val id: Long, val metric: Long, val value: Long, val date: LocalDate)