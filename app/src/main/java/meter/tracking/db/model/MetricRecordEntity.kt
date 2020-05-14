package meter.tracking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * A MetricRecordEntity is an holder for metric value at a time.
 * For instance, in january 2020, you made 24Km for metric 'car 1'
 *
 * @param id primary key
 * @param metricId metric foreign key
 * @param value current metric value for the date
 * @param date date of measure
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metricRecord",
        foreignKeys = [(ForeignKey(entity = MetricEntity::class, parentColumns = arrayOf("id"),
                childColumns = arrayOf("metricId")))],
        indices = arrayOf(Index(value = ["metricId"])))
class MetricRecordEntity(val metricId: Long, val value: Long, val date: LocalDate){

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}