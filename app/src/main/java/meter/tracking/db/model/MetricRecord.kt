package meter.tracking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * @param id primary key
 * @param metricId metric foreign key
 * @param value current metric value for the date
 * @param date date of measure
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metricRecord",
        foreignKeys = [(ForeignKey(entity = Metric::class, parentColumns = arrayOf("id"),
                childColumns = arrayOf("metricId")))],
        indices = arrayOf(Index(value = ["metricId"])))
class MetricRecord(@PrimaryKey val id: Long, val metricId: Long, val value: Long, val date: LocalDate)