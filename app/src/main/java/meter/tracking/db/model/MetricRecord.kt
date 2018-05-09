package meter.tracking.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metricRecord",
        foreignKeys = [(ForeignKey(entity = Metric::class, parentColumns = arrayOf("id"),
                                   childColumns = arrayOf("metric")))])
class MetricRecord(@PrimaryKey val id: Long, val metric: Long, val value: Long, val date: String)