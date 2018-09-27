package meter.tracking.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Metric entity
 * @param id primary key of this entity
 * @param name the name of this counter: eg: car one counter
 * @param value the last value of the counter. eg: 1523 miles
 * @param measureLabel the primary key of the [MeasuringType]. MeasuringType
 * @param historyFrequency kind of frequency of this counter (each day, each month, ...). For now, this is represented by an enum.
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metric",
        foreignKeys = [ForeignKey(entity = MeasuringType::class, parentColumns = ["label"],
                                  childColumns = ["measureLabel"])])
open class Metric(val name: String, val value: Long, @ColumnInfo(
        name = "measureLabel") val measureLabel: String,
                  val historyFrequency: HistoryFrequency){

    @PrimaryKey
    var id: Long = 0
}