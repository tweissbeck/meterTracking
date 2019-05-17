package meter.tracking.db.model

import androidx.room.Entity
import androidx.room.Relation
import java.time.LocalDate

/**
 * A [Metric] with a list of [MetricRecord]
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metric")
class MetricsWithRecord(name: String, value: Long, measureLabel: String,
                        historyFrequency: HistoryFrequency, startDate: LocalDate) :
        Metric(name, value, measureLabel, historyFrequency, startDate) {

    @Relation(parentColumn = "id", entityColumn = "metricId")
    lateinit var records: List<MetricRecord>

    override fun toString(): String {
        return "id:$id, name:$name, value:$value"
    }
}